package ch.heigvd.smtp;

import java.io.*;
import java.net.Socket;

/**
 * The SMTPClient class represents a simple SMTP client for sending emails.
 * It establishes a connection with an SMTP server, sends email requests, and disconnects.
 * It also provides methods to send emails using the SMTP protocol.
 *
 * The client connects to the server, sends the necessary SMTP commands for
 * email delivery, and then disconnects after sending the email.
 *
 * Instances of this class can be used to send emails by providing an Email object
 * containing the necessary information such as sender, receivers, subject, and body.
 *
 * @author [Author Name]
 */
public class SMTPClient {
    /* The SMTP server address */
    private String serverAddress;

    /* The port number for the SMTP server */
    private int port;

    /* The socket for communication with the SMTP server */
    private Socket socket;

    /* The buffered reader for reading responses from the SMTP server. */
    private BufferedReader reader;

    /* The buffered writer for sending SMTP requests to the server. */
    private BufferedWriter writer;

    /**
     * Constructs an SMTP client with the specified server address and port.
     *
     * @param serverAddress The SMTP server address.
     * @param port          The port number for the SMTP server.
     */
    public SMTPClient(String serverAddress, int port) {
        this.serverAddress = serverAddress;
        this.port = port;
    }

    /**
     * Establishes a connection with the SMTP server.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void connect() throws IOException {
        socket = new Socket(serverAddress, port);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
    }

    /**
     * Sends an SMTP request to the server.
     *
     * @param request The SMTP request to be sent.
     * @throws IOException If an I/O error occurs.
     */
    public void sendSMTPRequest(String request) throws IOException {
        writer.write(request + "\r\n");
        System.out.println("C: " + request);
        writer.flush();
    }

    /**
     * Receives and prints the response from the SMTP server.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void receiveResponse() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println("S: " + line);
            if (line.charAt(3) != '-') {
                break;
            }
        }
    }

    /**
     * Disconnects from the SMTP server.
     *
     * @throws IOException If an I/O error occurs.
     */
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

    /**
     * Sends an email using the SMTP protocol.
     *
     * @param e The Email object containing email information.
     * @throws IOException If an I/O error occurs during email transmission.
     */
    public void sendMail(Email e) throws IOException {

        System.out.println("---- sending email -----");
        this.connect();

        receiveResponse();

        sendSMTPRequest("ehlo " + this.serverAddress);
        receiveResponse();

        sendSMTPRequest("mail from:<" + e.getSender() + ">");
        receiveResponse();

        for (String rec : e.getReceivers()) {
            sendSMTPRequest("rcpt to:<" + rec + ">");
            receiveResponse();
        }

        sendSMTPRequest("data");
        receiveResponse();

        sendSMTPRequest("Content-Type: text/plain; charset=utf-8");
        sendSMTPRequest("from:<" + e.getSender() + ">");

        writer.write("to:" + e.getReceivers().get(0));
        for(int i = 1; i < e.getReceivers().size(); ++i){
            writer.write(", ");
            writer.write(", " + e.getReceivers().get(i));
        }
        writer.write("\r\n");
        writer.flush();

        sendSMTPRequest("subject: " + e.getSubject());
        sendSMTPRequest("\n");
        sendSMTPRequest(e.getBody());
        sendSMTPRequest(".");
        receiveResponse();

        sendSMTPRequest("quit");
        receiveResponse();

        reader.close();

        this.disconnect();
    }
}