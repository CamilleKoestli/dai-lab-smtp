package ch.heigvd.smtp;

import java.util.ArrayList;
import java.util.List;

/**
 * The EmailGroup class represents a group of email addresses with a designated sender and receivers.
 * It provides methods to add email addresses, retrieve email addresses, create email groups, and get sender and receivers.
 *
 * @author [Your Name]
 */
public class EmailGroup {

    private List<String> emailAddresses;
    private String sender;
    private List<String> receivers;

    /**
     * Constructs an EmailGroup object with empty lists for email addresses and receivers.
     */
    public EmailGroup() {
        emailAddresses = new ArrayList<>();
        receivers = new ArrayList<>();
    }

    /**
     * Adds an email address to the group. The first address added becomes the sender, and subsequent
     * addresses are added to the list of receivers.
     *
     * @param emailAddress The email address to add to the group.
     */
    public void addEmailAddress(String emailAddress) {
        if(sender == null) {
            sender = emailAddress;
        } else {
            receivers.add(emailAddress);
        }
    }

    /* Getters and setters */
    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public String getSender() {
        return sender;
    }

    public List<String> getReceivers() {
        return receivers;
    }
    public void setEmailAddresses(List<String> emailAddresses) {
        this.emailAddresses = emailAddresses;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }

}
