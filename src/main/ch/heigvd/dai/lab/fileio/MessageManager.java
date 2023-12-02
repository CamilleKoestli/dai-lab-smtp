package ch.heigvd.dai.lab.fileio;

import java.io.File;
import java.nio.charset.Charset;
import ch.heigvd.dai.lab.smtp.MessageCreator;
import ch.heigvd.dai.lab.smtp.EmailGroup;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageManager {

    private final File file;
    private final FileReaderWriter fileReaderWriter;
    private final EncodingSelector encodingSelector;

    public MessageManager(String file) {
        this.file = new File(file);
        this.fileReaderWriter = new FileReaderWriter();
        this.encodingSelector = new EncodingSelector();
    }

    public MessageCreator createRandomMessage() {
        // Utilisez la classe FileReaderWriter pour lire le contenu du fichier
        Charset encoding = encodingSelector.getEncoding(file);
        String messageContent = fileReaderWriter.readFile(file, encoding);

        // Supposons que MessageCreator ait un constructeur qui prend le contenu du message et crée un objet Message
        return new MessageCreator("", messageContent);
    }
    public List<EmailGroup> getGroupMails(String emailFile) {
        File emailListFile = new File(emailFile);
        List<EmailGroup> emailGroups = new ArrayList<>();

        // Read the list of emails from the file
        Charset encoding = encodingSelector.getEncoding(emailListFile);
        String emails = fileReaderWriter.readFile(emailListFile, encoding);

        // Split the emails by newline character
        String[] emailArray = emails.split("\\n");

        // Shuffle the array to get a random order
        List<String> shuffledEmails = new ArrayList<>(List.of(emailArray));
        Collections.shuffle(shuffledEmails);

        // Create EmailGroup objects with 2/3 emails in random order
        for (int i = 0; i < shuffledEmails.size(); i += 3) {
            EmailGroup emailGroup = new EmailGroup();
            for (int j = 0; j < 3 && i + j < shuffledEmails.size(); j++) {
                emailGroup.addEmailAddress(shuffledEmails.get(i + j));
            }
            emailGroups.add(emailGroup);
        }
        return emailGroups;
    }
}
