package ch.heigvd.config;

public class Configuration {

    public final static int PORT_NB = 1025;
    public final static String HOST_ADDRESS = "127.0.0.1";
    public final static String VICTIMS_FILE = "src/main/java/ch/heigvd/config/victims.json";
    public final static String MESSAGES_FILE = "src/main/java/ch/heigvd/config/messages.json";
    public static final int MIN_GROUP_SIZE = 2;
    public static final int MAX_GROUP_SIZE = 5;
    public static final String EMAIL_PATTERN = "^([A-Za-z0-9.]+)@([A-Za-z0-9]+)\\.([A-Za-z0-9]+)$";

}
