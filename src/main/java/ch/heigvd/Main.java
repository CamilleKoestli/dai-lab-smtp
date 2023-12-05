package ch.heigvd;

import ch.heigvd.smtp.*;
import ch.heigvd.fileio.MessageManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Création d'un groupe d'e-mails avec MessageManager
            MessageManager messageManager = new MessageManager();
            List<EmailGroup> emailGroups = messageManager.getGroupMailsFromJsonFile("src/main/java/ch/heigvd/config/emails.json");
            /*for(EmailGroup x : emailGroups){
                System.out.println("Sender : " + x.getSender());
                for(String y : x.getReceivers()){
                    System.out.println("Receivers :");
                    System.out.println(y);
                }
            }*/

/*            // Sélectionner le premier groupe
            EmailGroup emailGroup = emailGroups.get(0);
            System.out.println("sender: " + emailGroup.getSender() + ", receivers: " + emailGroup.getReceivers());  //TODO*/

            // Création d'un message avec MessageManager
            List<Message> messages = messageManager.readMessagesFromJsonFile("src/main/java/ch/heigvd/config/messages.json");
            Collections.shuffle(messages);
            // Select the first (random) message
            //Message selectedMessage = messages.get(0);      //TODO change
            int mIdx = 0;
            List<Email> emails = new ArrayList<>();

            for(EmailGroup eg : emailGroups){
                emails.add(new Email(eg, messages.get(mIdx)));
                mIdx = ++mIdx % messages.size();
            }
            /*for(Message m : messages){
                System.out.println("subject: "
                        + m.getSubject() + ", body: " + m.getBody());   //TODO
            }*/
/*
            Email email = new Email(emailGroup, selectedMessage);
            System.out.println("sender: " + email.getSender() + ", receivers: " + email.getReceivers() + ", subject: "
                    + email.getSubject() + ", body: " + email.getBody());   //TODO*/

            // Création d'un client SMTP avec le serveur et le port appropriés
            SMTPClient smtpClient = new SMTPClient("127.0.0.1", 1025);

            // Envoi du mail à chaque destinataire
            for(Email e : emails) {
                System.out.println("---------------------- MAIL ---------------------------");
                /*for (String receiver : e.getReceivers()) {
                    System.out.println("Dans boucle for...");
                    smtpClient.sendMail(e.getSender(), e.getReceivers(), e.getSubject(), e.getBody());
                    System.out.println("sender: " + e.getSender() + ", receivers: " + e.getReceivers() + ", subject: "
                            + e.getSubject() + ", body: " + e.getBody());
                    smtpClient.receiveResponse();
                }*/
                smtpClient.sendMail(e.getSender(), e.getReceivers(), e.getSubject(), e.getBody());
            }
            smtpClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
