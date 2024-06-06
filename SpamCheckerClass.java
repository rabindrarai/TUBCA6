import java.net.InetAddress;
import java.net.UnknownHostException;

public class SpamCheckerClass {
    private static final String SPAMHAUS_DNS = "zen.spamhaus.org";

    public static void main(String[] args) {
        String ipAddress = "142.250.194.164";
        try {
            if (isListedInSpamhaus(ipAddress)) {
                System.out.println(ipAddress + " is listed in Spamhaus.");
                return;
            }

            System.out.println(ipAddress + " is not listed in Spamhaus.");
        } catch (UnknownHostException e) {
            System.err.println("Error checking IP address: " + e.getMessage());
        }
    }

    private static boolean isListedInSpamhaus(String ipAddress) throws UnknownHostException {
        String query = getReverseIp(ipAddress) + "." + SPAMHAUS_DNS;
        try {
            InetAddress.getByName(query);
            return true; // If we get an address, it means the IP is listed
        } catch (UnknownHostException e) {
            return false; // If we get an UnknownHostException, it means the IP is not listed
        }
    }

    private static String getReverseIp(String ipAddress) {
        String[] parts = ipAddress.split("\\.");
        return parts[3] + "." + parts[2] + "." + parts[1] + "." + parts[0];
    }
}
