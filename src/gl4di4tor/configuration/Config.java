package gl4di4tor.configuration;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Gladiator on 6/11/2016.
 */
public class Config {
    private static Config instance = null;
    private String localInterface;
    private String routerInterface;
    private String trustedMacAddress;

    private Config() throws IOException {
        loadConfiguration();
    }

    public static Config getInstance() throws IOException {
        if (instance == null) {
            instance = new Config();
        }
        return instance;
    }

    private void loadConfiguration() throws IOException {
        File configFile = new File("configuration.properties");
        FileReader fileReader = new FileReader(configFile);
        Properties config = new Properties();
        config.load(fileReader);

        this.localInterface = config.getProperty("local.interface");
        this.routerInterface = config.getProperty("router.interface");
        this.trustedMacAddress = config.getProperty("trusted.mac");
    }

    public String getLocalInterface() {
        return localInterface;
    }

    public String getRouterInterface() {
        return routerInterface;
    }

    public String getTrustedMacAddress() {
        return trustedMacAddress;
    }
}
