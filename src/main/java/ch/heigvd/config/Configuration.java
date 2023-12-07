package ch.heigvd.config;

/**
 * The Configuration class contains constants used in the application.
 * It includes configuration settings such as server port, host address,
 * file paths for victims and messages, and constraints for group size and email pattern.
 *
 * This class provides a central location for configuration values that can be easily accessed
 * and modified as needed.
 *
 * Note: Modify the constants in this class to adapt the application to specific requirements.
 *
 * @author [Author Name]
 */
public class Configuration {

    /* The default port number for the SMTP server.*/
    public final static int PORT_NB = 1025;

    /*  The default host address for the SMTP server. */
    public final static String HOST_ADDRESS = "127.0.0.1";

    /* The file path for the victims JSON file. */
    public final static String VICTIMS_FILE = "src/main/java/ch/heigvd/config/victims.json";

    /* The file path for the messages JSON file. */
    public final static String MESSAGES_FILE = "src/main/java/ch/heigvd/config/messages.json";

    /* The minimum size allowed for an email group. */
    public static final int MIN_GROUP_SIZE = 2;

    /* The maximum size allowed for an email group. */
    public static final int MAX_GROUP_SIZE = 5;

    /* The regular expression pattern for validating email addresses. */
    public static final String EMAIL_PATTERN = "^([A-Za-z0-9.]+)@([A-Za-z0-9]+)\\.([A-Za-z0-9]+)$";

}
