package gl4di4tor.core;

import gl4di4tor.configuration.Config;
import gl4di4tor.ui.form.AlertPanel;
import gl4di4tor.utilities.RegularExpression;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DetectorEngine extends TimerTask {

    private static Pattern routerInterfaceRegex;
    private static Pattern ipv4Regex;
    private static Pattern macAddressRegex;

    DetectorEngine() throws IOException {
        routerInterfaceRegex = Pattern.compile(Config.getInstance().getRouterInterface() + "[^\\d]");
        ipv4Regex = Pattern.compile(RegularExpression.IPv4_REGEX);
        macAddressRegex = Pattern.compile(RegularExpression.MAC_ADDRESS_REGEX);
    }

    @Override
    public void run() {
        try {
            Process process = Runtime.getRuntime().exec("arp -a");
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            List<String> history = new ArrayList<>();
            String line;
            boolean ARPSpoofingFlag = false;
            String spoofingMacAddress = null;

            while ((line = reader.readLine()) != null) {
                history.add(line);
                Matcher matcher = routerInterfaceRegex.matcher(line);
                if (matcher.find()
                        && !(line.contains(Config.getInstance().getTrustedMacAddress()))) {
                    ARPSpoofingFlag = true;
                    spoofingMacAddress = this.detectSpoofingMacAddress(line);
                }
            }

            if (ARPSpoofingFlag) {
                String attackerIp = null;
                if (spoofingMacAddress != null) {
                    attackerIp = detectAttackerIP(history, spoofingMacAddress);
                }

                AlertPanel alert;
                if (attackerIp != null) {
                    alert = new AlertPanel(attackerIp);
                } else {
                    alert = new AlertPanel();
                }
                alert.show();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private String detectSpoofingMacAddress(String line) {
        Matcher macAddressMatcher = macAddressRegex.matcher(line);
        if (macAddressMatcher.find()) {
            return macAddressMatcher.group(0);
        }

        return null;
    }

    private String detectAttackerIP(List<String> history, String spoofingMacAddress) {
        for (String historyLine : history) {
            Matcher routerInterfaceMatcher = routerInterfaceRegex.matcher(historyLine);
            if (historyLine.contains(spoofingMacAddress) && !(routerInterfaceMatcher.find())) {
                Matcher ipMatcher = ipv4Regex.matcher(historyLine);
                if (ipMatcher.find()) {
                    return ipMatcher.group(0);
                }
            }
        }
        return null;
    }

}
