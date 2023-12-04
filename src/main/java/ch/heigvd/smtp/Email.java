package ch.heigvd.smtp;

import java.util.List;

public class Email {
    private String sender;
    private List<String> receivers;
    private String subject;
    private String body;

    public Email(String sender, List<String> receivers, String subject, String body) {
        this.sender = sender;
        this.receivers = receivers;
        this.subject = subject;
        this.body = body;
    }

    public Email(EmailGroup emailGroup, Message message) {
        this(emailGroup.getSender(), emailGroup.getReceivers(), message.getSubject(), message.getBody());
    }

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
