package framework.mailer;

import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.mailer.MailerBuilder;

public class ConfigProvider {

    public static Mailer getInstance(){
        return MailerBuilder
                .withSMTPServer("smtp.office365.com", 587, "adeshpande@idfbins.com", "321Happyhome")
                .withSessionTimeout(10 * 1000)
                .withProperty("mail.smtp.sendpartial", "true")
                .buildMailer();
    }
}
