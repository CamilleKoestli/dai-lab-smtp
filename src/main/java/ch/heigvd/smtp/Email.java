package ch.heigvd.smtp;

import java.util.List;

/**
 * The Email class represents an email with sender, receivers, subject, and body.
 * It provides methods to access and modify these attributes.
 *
 * @author [Author Name]
 */
public class Email {
    /**
     * The email sender address.
     */
    private String sender;

    /**
     * The list of email receivers.
     */
    private List<String> receivers;

    /**
     * The subject of the email.
     */
    private String subject;

    /**
     * The body content of the email.
     */
    private String body;

    /**
     * Constructs an Email with the specified sender, receivers, subject, and body.
     *
     * @param sender    The sender's email address.
     * @param receivers The list of receivers' email addresses.
     * @param subject   The subject of the email.
     * @param body      The body content of the email.
     */
    public Email(String sender, List<String> receivers, String subject, String body) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.body = body;
    }

    /**
     * Constructs an Email from an {@link EmailGroup} and a {@link Message}.
     *
     * @param emailGroup The email group containing sender and receivers.
     * @param message    The message to be sent in the email.
     */
    public Email(EmailGroup emailGroup, Message message) {
        this(emailGroup.getSender(), emailGroup.getReceivers(), message.getSubject(), message.getBody());
    }

    /* Getters and setters */
    public String getSender() {
        return sender;
    }
    public List<String> getReceivers() {
        return receivers;
    }
    public String getSubject() {
        return subject;
    }
    public String getBody() {
        return body;
    }
    public void setSender(String sender) {
        this.sender = sender;
    }
    public void setReceivers(List<String> receivers) {
        this.receivers = receivers;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public void setBody(String body) {
        this.body = body;
    }
}
