import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * The Spamhaus Block List (SBL) is a list of IP addresses that Spamhaus
 * considers to be involved in sending spam.
 * You can use the DNS-based Block List (DNSBL) to check if an IP address is
 * listed.
 * Here’s a simple Java example to perform this check using Java's DNS lookup
 * capabilities.
 *
 * First, you need to convert the IP address into a format suitable for querying
 * the Spamhaus DNSBL.
 * The format is d.e.c.b.zen.spamhaus.org, where a.b.c.d is the IP address in
 * reverse order.
 **/

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
        // Converts the IP address into the required reversed format for querying the DNSBL.
        String[] parts = ipAddress.split("\\.");
        return parts[3] + "." + parts[2] + "." + parts[1] + "." + parts[0];
    }
}
