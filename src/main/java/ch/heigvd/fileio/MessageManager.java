package ch.heigvd.fileio;

import ch.heigvd.smtp.*;

import java.awt.*;
import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MessageManager {

    private final FileReaderWriter fileReaderWriter = new FileReaderWriter();
    private final EncodingSelector encodingSelector = new EncodingSelector();

    public MessageManager() {}

    public List<Message> readContentFromFile(String filePath) {
        List<Message> messages = new ArrayList<>();

        File file = new File(filePath);
        Charset encoding = encodingSelector.getEncoding(file);
        String fileContent = fileReaderWriter.readFile(file, encoding);

        String[] messageSections = fileContent.split("--------");

        for (int i = 0; i < messageSections.length; i++) {
            String section = messageSections[i].trim();

            // Skip empty sections
            if (section.isEmpty()) {
                continue;
            }

            String[] lines = section.split("\n");

            // Set default in case section has no subject/body
            Message m = new Message("", "");

            for (String line : lines) {
                line = line.trim();
                if (line.startsWith("Subject:")) {
                    m.setSubject(line.substring("Subject:".length()).trim());
                } else if (line.startsWith("Body:")) {
                    m.setBody(line.substring("Body:".length()).trim());
                }
            }
            messages.add(m);
        }
        return messages;
    }

    public List<EmailGroup> getGroupMails(String emailFilePath) {
        File emailListFile = new File(emailFilePath);
        List<EmailGroup> emailGroups = new ArrayList<>();

        // Read the list of emails from the file
        Charset encoding = encodingSelector.getEncoding(emailListFile);
        String emails = fileReaderWriter.readFile(emailListFile, encoding);

        // Split the emails by newline character
        String[] emailArray = emails.split("\n");

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

    //TODO EmailValidator

}
