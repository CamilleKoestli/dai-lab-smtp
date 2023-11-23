package java.ch.heigvd.dai.lab.smtp;

import java.util.ArrayList;
import java.util.List;

public class EmailGroup {

    private List<String> emailAddresses;

    public EmailGroup() {
        emailAddresses = new ArrayList<>();
    }

    public void addEmailAddress(String emailAddress) {
        emailAddresses.add(emailAddress);
    }

    public List<String> getEmailAddresses() {
        return emailAddresses;
    }

    public void createGroup( List<String> nameGroup) {
        nameGroup = getEmailAddresses();
    }

}