package java.ch.heigvd.dai.lab.smtp;

import java.io.*;
import java.net.Socket;

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
        try (BufferedWriter writer = this.writer) {
            writer.write(request + "\r\n");
            writer.flush();
        }
    }

    public String receiveResponse() throws IOException {
        try (BufferedReader reader = this.reader) {
            return reader.readLine();
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
            socket = null;
            reader = null;
            writer = null;
        }
    }

    public void sendMail(String from, String to, String subject, String body) throws IOException {
        try (BufferedWriter writer = this.writer) {
            sendSMTPRequest("ehlo" + this.serverAddress);
            receiveResponse();

            sendSMTPRequest("mail from:<" + from + ">");
            receiveResponse();

            sendSMTPRequest("rcpt to:<" + to + ">");
            receiveResponse();

            sendSMTPRequest("data");
            receiveResponse();

            sendSMTPRequest("Subject: " + subject);
            sendSMTPRequest("");
            sendSMTPRequest(body);
            sendSMTPRequest(".");
            receiveResponse();

            sendSMTPRequest("quit");
            receiveResponse();
        }
    }
}