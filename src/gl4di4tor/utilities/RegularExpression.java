package gl4di4tor.utilities;

/**
 * Created by gladiator on 8/22/16.
 */
public class RegularExpression {
    public static final String IPv4_REGEX = "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
            "([01]?\\d\\d?|2[0-4]\\d|25[0-5])";

    public static final String MAC_ADDRESS_REGEX = "([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})";
}
