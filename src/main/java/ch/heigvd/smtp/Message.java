package ch.heigvd.smtp;

/**
 * The Message class represents an email message with a subject and body.
 * It provides methods to retrieve and modify the subject and body.
 *
 * @author [Your Name]
 */
public class Message{
    private String subject;
    private String body;

    /**
     * Constructs a Message object with the specified subject and body.
     *
     * @param subject The subject of the email message.
     * @param body    The body of the email message.
     */
    public Message(String subject, String body){
        this.subject = subject;
        this.body = body;
    }

    /* Getters and setters */
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