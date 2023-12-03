package ch.heigvd.smtp;

public class Message{
    private String subject;
    private String body;
    
    public Message(String subject, String body){
        this.subject = subject;
        this.body = body;
    }

    public String getSubject(){
        return subject;
    }

    public String getBody(){
        return body;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}