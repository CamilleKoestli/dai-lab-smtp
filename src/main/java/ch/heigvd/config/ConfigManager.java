package ch.heigvd.config;

import ch.heigvd.smtp.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The ConfigManager class manages the configuration for the email prank application.
 * It reads messages and email groups from JSON files.
 */
public class ConfigManager {

    /* Number of groups for the e-mail prank */
    private int nbGroups;

    /* Messages to be sent */
    public List<Message> messages;

    /* Email groups containing sender and receivers of the messages */
    public List<EmailGroup> emailGroups;

    /**
     * Constructs a ConfigManager with the specified number of groups and file paths.
     *
     * @param nbGroups           The number of groups for the email prank.
     * @param messagesFilePath   The file path to the JSON file containing messages.
     * @param victimsFilePath    The file path to the JSON file containing victim emails.
     * @throws IOException if there is an issue reading the JSON files.
     */
    public ConfigManager(int nbGroups, String messagesFilePath, String victimsFilePath) throws IOException {
        try {
            this.nbGroups = nbGroups;
            readMessagesFromJsonFile(messagesFilePath);
            getGroupMailsFromJsonFile(victimsFilePath);
        } catch (IOException e){
            System.err.println("Error reading configuration files: " + e.getMessage());
        }
    }

    /**
     * Reads messages from a JSON file and shuffles them.
     *
     * @param messagesList The file path to the JSON file containing messages.
     * @throws IOException if there is an issue reading the JSON file.
     */
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

        // Iterate through each "messageNode" in "messagesNode"
        for (JsonNode messageNode : messagesNode) {
            // Extract "subject" and "body" from each "message"
            subject = messageNode.get("subject").asText();
            body = messageNode.get("body").asText();

            // Create Message object and add it to the list
            messages.add(new Message(subject, body));
        }

        //Shuffle messages
        Collections.shuffle(messages);

        this.messages = messages;
    }

    /**
     * Reads email addresses from a JSON file, shuffles them, and creates email groups.
     *
     * @param victimsFilePath The file path to the JSON file containing victim emails.
     * @throws IOException if there is an issue reading the JSON file.
     */
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

    /**
     * Gets the number of groups for the email prank.
     * @return The number of groups.
     */
    public int getNbGroups() {
        return nbGroups;
    }

    //TODO EmailValidator

}
