package ch.heigvd;

import ch.heigvd.smtp.*;
import ch.heigvd.fileio.MessageManager;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            /*
            // Création d'un groupe d'e-mails
            EmailGroup emailGroup = new EmailGroup();

            // Lire les e-mails depuis le fichier
            emailGroup.readEmailsFromFile("src/main/java/ch/heigvd/config/emails.utf8");

            // Créer le groupe
            emailGroup.createGroup();*/

            // Création d'un groupe d'e-mails avec MessageManager
            MessageManager messageManager = new MessageManager("src/main/java/ch/heigvd/config/emails.utf8");
            List<EmailGroup> emailGroups = messageManager.getGroupMails("src/main/java/ch/heigvd/config/emails.utf8");

            // Sélectionner le premier groupe
            EmailGroup emailGroup = emailGroups.get(0);

            // Accéder au sender et aux receivers
            String sender = emailGroup.getSender();
            List<String> receivers = emailGroup.getReceivers();

            /*
            Message messageCreator = new Message("Sujet test", "Corps test");

            // Création d'un client SMTP avec le serveur et le port appropriés
            Email email = Email.createEmailForGroup(emailGroup, messageCreator );*/

            // Création d'un message avec MessageManager
            Message message = messageManager.createRandomMessage();
            Email email = emailGroup.createEmailForGroup(emailGroup, message);

            // Création d'un client SMTP avec le serveur et le port appropriés
            SMTPClient smtpClient = new SMTPClient("127.0.0.1", 1025);

            /*
            // Création d'un objet MessageReader à partir du chemin du fichier de configuration
            MessageReader messageReader = new MessageReader(smtpClient, "src/main/java/ch/heigvd/config/messages.utf8");

            */

            // Envoi du mail à chaque destinataire
            for (String receiver : receivers) {
                smtpClient.connect();
                smtpClient.sendMail(sender, receiver, email.getSubject(), email.getBody());
                smtpClient.receiveResponse();
            }
            smtpClient.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
