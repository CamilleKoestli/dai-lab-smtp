package ch.heigvd.config;

import ch.heigvd.smtp.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConfigManager {

    /* Number of groups for the e-mail prank */
    public int nbGroups;
    public List<Message> messages;
    public List<EmailGroup> emailGroups;

    public ConfigManager(int nbGroups, String messagesFilePath, String victimsFilePath) throws IOException {
        this.nbGroups = nbGroups;
        readMessagesFromJsonFile(messagesFilePath);
        getGroupMailsFromJsonFile(victimsFilePath);
    }

    public void readMessagesFromJsonFile(String messagesList) throws IOException {
        List<Message> messages = new ArrayList<>();
        File file = new File(messagesList);

        String subject;
        String body;

        // Create ObjectMapper instance
        ObjectMapper objectMapper = new ObjectMapper();

        // Read JSON file and parse it into a JsonNode
        JsonNode jsonNode = objectMapper.readTree(file);

        // Get the "messages" node
        JsonNode messagesNode = jsonNode.get("messages");

        // Iterate through each "message" in "messages"
        for (JsonNode messageNode : messagesNode) {
            // Extract "subject" and "body" from each "message"
            subject = messageNode.get("subject").asText();
            body = messageNode.get("body").asText();

            // Create Message object and add it to the list
            Message message = new Message(subject, body);
            messages.add(message);
        }

        //Shuffle messages
        Collections.shuffle(messages);

        this.messages = messages;
    }

    public void getGroupMailsFromJsonFile(String victimsFilePath) throws IOException {

        List<String> emails = new ArrayList<>();
        List<EmailGroup> emailGroups = new ArrayList<>();

        File file = new File(victimsFilePath);
        String email;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode emailsNode = jsonNode.get("emails");

        for (JsonNode node : emailsNode) {
            email = node.get("mail").asText();
            emails.add(email);
        }

        //Shuffle emails
        Collections.shuffle(emails);

        // Create EmailGroup objects
        int idx;
        for (int i = 0; i < Configuration.NB_GROUP; ++i) {
            EmailGroup emailGroup = new EmailGroup();
            for (int j = 0; j < emails.size() / Configuration.NB_GROUP; ++j) {
                idx = i * Configuration.NB_GROUP + j;
                if (idx < emails.size()) {
                    emailGroup.addEmailAddress(emails.get(idx));
                }
            }
            emailGroups.add(emailGroup);
        }

        System.out.println(emailGroups);

        this.emailGroups = emailGroups;
    }

    //TODO EmailValidator

}
