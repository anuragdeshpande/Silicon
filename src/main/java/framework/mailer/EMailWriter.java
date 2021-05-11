package framework.mailer;


import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.File;
import java.util.Objects;

public class EMailWriter {

    public static Mailer getInternalMailer(){
        return MailerBuilder
                .withSMTPServer("mail.idfbins.com", 25, "adeshpande@idfbins.com", "321eHappyhom")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withProperty("mail.smtp.sendpartial", "true")
                .clearEmailAddressCriteria()
                .withDebugLogging(true)
                .buildMailer();


    }

    public static void main(String[] args) {
        Email email = EmailBuilder.startingBlank()
                .to("Rusty Young", "ryoung@idfbins.com")
                .to("Anurag Deshpande", "adeshpande@idfbins.com")
                .to("Adam Waldron", "awaldron@idfbins.com")
                .withSubject("Test Email: Automation Report and Updates. Open in phone and computer :).")
                .from("Anurag Deshpande", "adeshpande@idfbins.com")
                .withHTMLText(new File(Objects.requireNonNull(EMailWriter.class.getClassLoader().getResource("Sample.html")).getPath()))
                .buildEmail();

        getInternalMailer().sendMail(email);
    }
}
