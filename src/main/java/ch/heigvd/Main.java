package ch.heigvd;

import ch.heigvd.config.Configuration;
import ch.heigvd.smtp.*;
import ch.heigvd.config.ConfigManager;

import java.io.IOException;

/**
 * The Main class represents the entry point for the email prank application.
 * It reads configuration settings, communicates with an SMTP server, and sends email pranks.
 *
 * @author Vitoria Oliveira
 * @author Camille Koestli
 */
public class Main {

    /**
     * The main method of the email prank application.
     *
     * @param args Command-line arguments. The first argument, if provided, represents the number of groups.
     */
    public static void main(String[] args) {
        try {
            // Read configuration settings from files and command-line arguments
            ConfigManager configManager = new ConfigManager(args, Configuration.MESSAGES_FILE, Configuration.VICTIMS_FILE);

            // Create an SMTP client with the specified server address and port
            SMTPClient smtpClient = new SMTPClient(Configuration.HOST_ADDRESS, Configuration.PORT_NB);

            // Send email pranks for each group
            for(int i = 0; i < configManager.getNbGroups(); ++i){
                smtpClient.sendMail(new Email(configManager.emailGroups.get(i), configManager.messages.get(i)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
