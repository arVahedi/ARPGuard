package gl4di4tor.core;

import gl4di4tor.configuration.Config;
import gl4di4tor.ui.form.AlertPanel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class DetectorEngine extends TimerTask {

    @Override
    public void run() {
        try {
            Process process = Runtime.getRuntime().exec("arp -a");
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            List<String> history = new ArrayList<String>();
            String line;
            boolean ARPSpoofingFlag = false;
            String spoofingMacAddress = null;
            while ((line = reader.readLine()) != null) {
                history.add(line);
                if (line.contains(Config.getInstance().getRouterInterface())
                        && !(line.contains(Config.getInstance().getTrustedMacAddress()))) {
                    ARPSpoofingFlag = true;
                    String[] lineSplit = line.split(" ");
                    for (String tmp : lineSplit) {
                        if (tmp.contains("-")) {
                            spoofingMacAddress = tmp;
                        }
                    }
                }
            }

            if (ARPSpoofingFlag) {
                String attackerIp = null;

                if (spoofingMacAddress != null) {
                    for (String item : history) {
                        if (item.contains(spoofingMacAddress) && !(item.contains(Config.getInstance().getRouterInterface()))) {
                            String[] lineSplit = item.split(" ");
                            for (String tmp : lineSplit) {
                                if (tmp.contains(".")) {
                                    attackerIp = tmp;
                                }
                            }
                        }
                    }
                }

                AlertPanel alert;
                if (attackerIp != null) {
                    alert = new AlertPanel(attackerIp);
                } else {
                    alert = new AlertPanel();
                }
                alert.show();
            }

            /*AlertPanel alert = new AlertPanel();
            alert.show();*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
