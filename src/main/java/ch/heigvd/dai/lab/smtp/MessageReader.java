package java.ch.heigvd.dai.lab.smtp;

import java.io.*;
import static java.nio.charset.StandardCharsets.UTF_16;

public class MessageReader extends MessageCreator{

    public MessageReader(String configPath) throws IOException {
        super(readSubjectMail(configPath), readBodyMail(configPath));
    }


    private static String readSubjectMail(String mailPath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(mailPath))) {
            // Assuming the first line is the subject
            return reader.readLine();
        }
    }

    private static String readBodyMail(String mailPath) throws IOException {
        StringBuilder body = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(mailPath))) {
            String line;
            boolean subjectRead = false;

            while ((line = reader.readLine()) != null) {
                if (subjectRead) {
                    body.append(line).append("\n");
                }
            }
        }
        return body.toString();
    }



}