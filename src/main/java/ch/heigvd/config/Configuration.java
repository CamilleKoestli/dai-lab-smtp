package ch.heigvd.config;

public class Configuration {

    public final static int PORT_NB = 1025;
    public final static String HOST_ADDRESS = "127.0.0.1";
    public final static String VICTIMS_FILE = "src/main/java/ch/heigvd/config/victims.json";
    public final static String MESSAGES_FILE = "src/main/java/ch/heigvd/config/messages.json";

    public static final int MIN_GROUP_SIZE = 2;
    public static final int MAX_GROUP_SIZE = 5;

    //TODO get nb_group from terminal
    public static final int NB_GROUP = 3;
}
