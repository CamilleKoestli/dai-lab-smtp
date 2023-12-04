package ch.heigvd;

import ch.heigvd.smtp.*;
import ch.heigvd.fileio.MessageManager;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Création d'un groupe d'e-mails avec MessageManager
            MessageManager messageManager = new MessageManager();
            List<EmailGroup> emailGroups = messageManager.getGroupMails("src/main/java/ch/heigvd/config/emails.utf8");

            // Sélectionner le premier groupe
            EmailGroup emailGroup = emailGroups.get(0);
            System.out.println("sender: " + emailGroup.getSender() + ", receivers: " + emailGroup.getReceivers());  //TODO

            // Création d'un message avec MessageManager
            List<Message> messages = messageManager.readContentFromFile("src/main/java/ch/heigvd/config/messages.utf8");
            Collections.shuffle(messages);
            // Select the first (random) message
            Message selectedMessage = messages.get(0);      //TODO change

            for(Message m : messages){
                System.out.println("subject: "
                        + m.getSubject() + ", body: " + m.getBody());   //TODO
            }

            Email email = new Email(emailGroup, selectedMessage);
            System.out.println("sender: " + email.getSender() + ", receivers: " + email.getReceivers() + ", subject: "
                    + email.getSubject() + ", body: " + email.getBody());   //TODO

            // Création d'un client SMTP avec le serveur et le port appropriés
            SMTPClient smtpClient = new SMTPClient("127.0.0.1", 1025);

            smtpClient.connect();
            // Envoi du mail à chaque destinataire
            for (String receiver : emailGroup.getReceivers()) {
                System.out.println("Dans boucle for...");
                smtpClient.sendMail(emailGroup.getSender(), emailGroup.getReceivers(), email.getSubject(), email.getBody());
                System.out.println("sender: " + email.getSender() + ", receivers: " + email.getReceivers() + ", subject: "
                        + email.getSubject() + ", body: " + email.getBody());
                smtpClient.receiveResponse();
            }
            smtpClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
