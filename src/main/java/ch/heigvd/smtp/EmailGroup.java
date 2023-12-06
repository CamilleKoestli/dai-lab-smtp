package ch.heigvd.smtp;

import ch.heigvd.config.ConfigManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmailGroup {

    private List<String> emailAddresses;
    private String sender;
    private List<String> receivers;

    public EmailGroup() {
        emailAddresses = new ArrayList<>();
        receivers = new ArrayList<>();
    }

    public void addEmailAddress(String emailAddress) {
        if(sender == null) {
            sender = emailAddress;
        } else {
            receivers.add(emailAddress);
        }
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void createGroup() {
        if (!emailAddresses.isEmpty()) {
            // Le premier dans la liste est le sender
            sender = emailAddresses.get(0);

            // Le reste sont les receivers
            if (emailAddresses.size() > 1) {
                receivers = new ArrayList<>(emailAddresses.subList(1, emailAddresses.size()));
            }
        }
    }

    public String getSender() {
        return sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }

}
