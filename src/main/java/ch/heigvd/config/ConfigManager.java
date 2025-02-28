package ch.heigvd.config;

import ch.heigvd.smtp.*;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The ConfigManager class manages the configuration for the email prank application.
 * It reads messages and email groups from JSON files.
 */
public class ConfigManager {
    /* Min and max number of groups accepted */
    public static int MIN_NB_GROUPS;
    public static int MAX_NB_GROUPS;

    /* Number of groups for the e-mail prank */
    private int nbGroups;

    /* List of e-mails for pranks */
    List<String> emails;

    /* Email groups containing sender and receivers of the messages */
    public List<EmailGroup> emailGroups;

    /* Messages to be sent */
    public List<Message> messages;


    /**
     * Constructs a ConfigManager with the specified number of groups and file paths.
     *
     * @param messagesFilePath   The file path to the JSON file containing messages.
     * @param victimsFilePath    The file path to the JSON file containing victim emails.
     * @throws IOException if there is an issue reading the JSON files.
     */
    public ConfigManager(String[] args, String messagesFilePath, String victimsFilePath) throws IOException {
        try {
            readMessagesFromJsonFile(messagesFilePath);
            getEmailsFromJsonFile(victimsFilePath);
            this.nbGroups = getNbGroupsFromCommandLine(args);
            setEmailGroups();
        } catch (IOException e){
            System.err.println("Error reading configuration files: " + e.getMessage());
        }
    }

    /**
     * Gets the number of groups from the command line arguments, ensuring it is within
     * the allowed range. If not specified or invalid, prompts the user for input.
     *
     * @param args The command line arguments.
     * @return The number of groups.
     */
    public static int getNbGroupsFromCommandLine(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int nbGroups;
        boolean validInput = false;

        do {
            // Asks for user input
            System.out.print("Enter the number of groups (between " + MIN_NB_GROUPS + " and " + MAX_NB_GROUPS + "): ");
            nbGroups = scanner.nextInt();
            validInput = validUserInput(nbGroups);
            if(!validInput){
                System.err.println("Arguments non valides. Veuillez essayer à nouveau.");
            }

        } while (!validInput);

        return nbGroups;
    }

    public static boolean validUserInput(int input){
        return (input >= Configuration.MIN_GROUP_SIZE && input <= Configuration.MAX_GROUP_SIZE);
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
    public void getEmailsFromJsonFile(String victimsFilePath) throws IOException {
        List<String> emails = new ArrayList<>();
        File file = new File(victimsFilePath);
        String email;

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(file);
        JsonNode emailsNode = jsonNode.get("emails");

        for (JsonNode node : emailsNode) {
            email = node.get("mail").asText();
            if(!isValidEmailAddress(email)){
                System.err.println("Invalid email address: " + email);
                continue;
            }
            emails.add(email);
        }

        // Set min and max nb of groups
        setMinMaxNbGroups(emails.size());

        //Shuffle emails
        Collections.shuffle(emails);

        this.emails = emails;
    }

    public void setEmailGroups(){
        List<EmailGroup> emailGroups = new ArrayList<>();

        int pPerGroup = emails.size() / nbGroups;
        int idx;

        for (int i = 0; i < nbGroups; ++i) {
            EmailGroup emailGroup = new EmailGroup();
            for (int j = 0; j < pPerGroup; ++j) {
                idx = i * pPerGroup + j;
                if (idx < emails.size()) {
                    emailGroup.addEmailAddress(emails.get(idx));
                }
            }
            emailGroups.add(emailGroup);
        }

        this.emailGroups = emailGroups;
    }

    /**
     * Validates an email address using a regular expression pattern.
     *
     * @param email The email address to validate.
     * @return True if the email address is valid, false otherwise.
     *
     * @see <a href="https://www.javatpoint.com/java-email-validation">JavaTpoint Email Validation</a>
     */
    public static boolean isValidEmailAddress(String email){
        Pattern pattern = Pattern.compile(Configuration.EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * Sets number of given emails.
     */
    private void setMinMaxNbGroups(int emailsListSize){
        this.MIN_NB_GROUPS = emailsListSize / Configuration.MAX_GROUP_SIZE;
        this.MAX_NB_GROUPS = emailsListSize / Configuration.MIN_GROUP_SIZE;
    }

    /**
     * Gets the number of groups for the email prank.
     * @return The number of groups.
     */
    public int getNbGroups() {
        return nbGroups;
    }
}
