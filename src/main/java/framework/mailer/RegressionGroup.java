package framework.mailer;

import org.simplejavamail.email.Recipient;

import java.util.ArrayList;

public class RegressionGroup {
    private String name;
    private ArrayList<Recipient> recipientList;

    public RegressionGroup(){
        this.recipientList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Recipient> getRecipientList() {
        return recipientList;
    }

    public void addRecipient(Recipient recipient){
        this.recipientList.add(recipient);
    }
}
