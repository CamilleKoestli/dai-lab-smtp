package java.ch.heigvd.dai.lab.smtp;

import java.io.*;
import java.net.Socket;

public class SMTPClient {
    private String serverAddress;
    private int port;
    private Socket socket;
    private BufferedReader reader;
    private BufferedWriter writer;

    public SMTPClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    public void connect() throws IOException {
        socket = new Socket(serverAddress, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    public void sendSMTPRequest(String request) throws IOException {
        writer.write(request + "\r\n");
        writer.flush();
    }

    public String receiveResponse() throws IOException {
        return reader.readLine();
    }

    public void sendMail(String serverAddress, String from, String to, String subject, String body) throws IOException {
        sendSMTPRequest("ehlo" + serverAddress);
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