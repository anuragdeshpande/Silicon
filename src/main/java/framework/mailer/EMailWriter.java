package framework.mailer;

import org.simplejavamail.email.Email;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.email.EmailPopulatingBuilder;


public class EMailWriter {
    private EmailPopulatingBuilder emailConfig;
    private Email email;

    private EMailWriter(){
        this.emailConfig = EmailBuilder.startingBlank().from("Regression Bot", "adeshpande@idfbins.com");
    }

    public static EMailWriter writeNewEMail(){
        return new EMailWriter();
    }

    public void sendRegressionReport(RegressionGroup group, String reportPath){
        StringBuilder builder = new StringBuilder();
        builder.append("Hello,<br>")
                .append("Automated Test Run is complete. Please <a href=\">")
                .append(reportPath).append("\">Click here </a> to see the report.<br><br>")
                .append("A Robot.");

        EMailWriter eMailWriter = writeNewEMail();
        eMailWriter.configureEmail().to(group.getRecipientList()).withSubject(group.getName() + ": Regression Report");
        eMailWriter.sendEMail();

    }

    public void sendEMail(){
        this.email = emailConfig.buildEmail();
        ConfigProvider.getInstance().sendMail(email);
    }

    public EmailPopulatingBuilder configureEmail() {
        return emailConfig;
    }
}
