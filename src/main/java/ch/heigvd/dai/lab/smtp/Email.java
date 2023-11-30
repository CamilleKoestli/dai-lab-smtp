package java.ch.heigvd.dai.lab.smtp;

import java.util.List;

public class Email {
    private String sender;
    private String receiver;
    private String subject;
    private String body;

    public Email(String sender, String receiver, String subject, String body) {
        this.sender = sender;
        this.receiver = receiver;
        this.subject = subject;
        this.body = body;
    }

    public static Email createEmailFromGroup(EmailGroup emailGroup, MessageCreator messageCreator) {
        emailGroup.createGroup();
        String sender = emailGroup.getSender();
        List<String> receivers = emailGroup.getReceivers();

        String receiver = (receivers != null && !receivers.isEmpty()) ? receivers.get(0) : null;

        String subject = messageCreator.getSubject();
        String body = messageCreator.getBody();

        return new Email(sender, receiver, subject, body);
    }
}
