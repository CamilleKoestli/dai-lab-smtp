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

            // Création d'un message avec MessageManager
            List<Message> messages = messageManager.readMessagesFromJsonFile("src/main/java/ch/heigvd/config/messages.json");
            Collections.shuffle(messages);

            int mIdx = 0;
            List<Email> emails = new ArrayList<>();

            for(EmailGroup eg : emailGroups){
                emails.add(new Email(eg, messages.get(mIdx)));
                mIdx = ++mIdx % messages.size();
            }

            // Création d'un client SMTP avec le serveur et le port appropriés
            SMTPClient smtpClient = new SMTPClient("127.0.0.1", 1025);

            // Envoi du mail à chaque destinataire
            for(Email e : emails) {
                System.out.println("---------------------- MAIL ---------------------------");
                smtpClient.sendMail(e.getSender(), e.getReceivers(), e.getSubject(), e.getBody());
            }
            smtpClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
