package framework.mailer;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.EmailPopulatingBuilder;

public class EMailWriter {
    private EmailPopulatingBuilder emailConfig;

    private EMailWriter(){
        this.emailConfig = EmailBuilder.startingBlank();
    }

    public EMailWriter writeNewEMail(){
        return new EMailWriter();
    }

    public void sendRegressionReport(EmailList list, String reportLocation){
    }

    public EmailPopulatingBuilder configureEmail() {
        return emailConfig;
    }
}
