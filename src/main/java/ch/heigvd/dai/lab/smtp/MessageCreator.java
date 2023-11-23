package java.ch.heigvd.dai.lab.smtp;

import java.ch.heigvd.dai.lab.smtp.SMTPClient;

public class MessageCreator{
    private String subject;
    private String body;
    
    public MessageCreator(String subject, String body){
        this.subject = subject;
        this.body = body;

    }

    public String getSubject(){
        return subject;
    }

    public String getBody(){
        return body;
    }
}