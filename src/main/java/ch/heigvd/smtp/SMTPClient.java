package ch.heigvd.smtp;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class SMTPClient {
    private String serverAddress;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;
    private String lastSentSubject;
    private String lastSentBody;


    public SMTPClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public String getSubject() {
        return lastSentSubject;
    }

    public String getBody() {
        return lastSentBody;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendSMTPRequest(String request) throws IOException {
        writer.write(request + "\r\n");
        System.out.println("C: " + request);
        writer.flush();
    }

    public void receiveResponse() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("S: " + line);
            if (line.charAt(3) != '-') {
                break;
            }
        }
    }

    public void disconnect() throws IOException {
        if (socket != null && !socket.isClosed()) {
            try {
                socket.close();
            } catch (IOException e) {
                // Handle the IOException if needed
                e.printStackTrace();
            }

            // Set the socket, reader, and writer to null after closing
            reader.close();
            writer.close();

            socket = null;
            reader = null;
            writer = null;
        }
    }

    public void sendMail(String from, List<String> to, String subject, String body) throws IOException {

        this.connect();

        receiveResponse();

        sendSMTPRequest("ehlo " + this.serverAddress);
        receiveResponse();

        for (String rec : to) {
            sendSMTPRequest("mail from:<" + from + ">");
            receiveResponse();

            sendSMTPRequest("rcpt to:<" + rec + ">");
            receiveResponse();

            sendSMTPRequest("data");
            receiveResponse();

            //TODO
            sendSMTPRequest("Content-Type: text/plain; charset=utf-8");
            sendSMTPRequest("from:<" + from + ">");
            sendSMTPRequest("to:<" + rec + ">");
            sendSMTPRequest("Subject: " + subject);
            sendSMTPRequest("\n");
            sendSMTPRequest("Body: "+ body);
            sendSMTPRequest(".");
            receiveResponse();
        }

        sendSMTPRequest("quit");
        receiveResponse();

        reader.close();
    }
}