package java.ch.heigvd.dai.lab.smtp;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try {
            // Création d'un groupe d'e-mails
            EmailGroup emailGroup = new EmailGroup();

            // Lire les e-mails depuis le fichier
            emailGroup.readEmailsFromFile("src/main/java/ch/heigvd/dai/lab/config/emails.utf8");

            // Créer le groupe
            emailGroup.createGroup();

            // Accéder au sender et aux receivers
            String sender = emailGroup.getSender();
            List<String> receivers = emailGroup.getReceivers();

            // Création d'un objet MessageReader à partir du chemin du fichier de configuration
            MessageReader messageReader = new MessageReader("src/main/java/ch/heigvd/dai/lab/config/messages.utf8");

            MessageCreator messageCreator = new MessageCreator("Sujet test", "Corps tes");

            // Création d'un client SMTP avec le serveur et le port appropriés
            Email email = Email.createEmailFromGroup(emailGroup, messageCreator );

            // Création d'un client SMTP avec le serveur et le port appropriés
            SMTPClient smtpClient = new SMTPClient("127.0.0.1", 25);

            // Envoi du mail à chaque destinataire
            for (String receiver : emailGroup.getReceivers()) {
                smtpClient.connect();
                smtpClient.sendMail("smtp.example.com", sender, receiver, messageCreator.getSubject(), messageCreator.getBody());
                smtpClient.receiveResponse();
                //smtpClient.disconnect();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
