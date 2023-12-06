package ch.heigvd;

import ch.heigvd.config.Configuration;
import ch.heigvd.smtp.*;
import ch.heigvd.config.ConfigManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            ConfigManager configManager = new ConfigManager(args, Configuration.MESSAGES_FILE, Configuration.VICTIMS_FILE);

            SMTPClient smtpClient = new SMTPClient(Configuration.HOST_ADDRESS, Configuration.PORT_NB);

            for(int i = 0; i < configManager.getNbGroups(); ++i){
                smtpClient.sendMail(new Email(configManager.emailGroups.get(i), configManager.messages.get(i)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
