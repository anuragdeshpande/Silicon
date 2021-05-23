package framework.mailer;


import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.mailer.MailerBuilder;

public class EMailWriter {

    public static Mailer getInternalMailer(){
        return MailerBuilder
                .withSMTPServer("mail.idfbins.com", 25, "testupdates@idfbins.com", "gMj10ilqeIfvLC3fLQHp")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withProperty("mail.smtp.sendpartial", "true")
                .clearEmailAddressCriteria()
                .withDebugLogging(true)
                .buildMailer();


    }

//    public static void main(String[] args) {
//        Email email = EmailBuilder.startingBlank()
//                .to("Rusty Young", "ryoung@idfbins.com")
//                .to("Anurag Deshpande", "adeshpande@idfbins.com")
//                .to("Adam Waldron", "awaldron@idfbins.com")
//                .withReplyTo("qateam@idfbins.com")
//                .withSubject("Test Email: Service account testing.")
//                .from("Automation Updates", "testupdates@idfbins.com")
//                .withHTMLText(new File(Objects.requireNonNull(EMailWriter.class.getClassLoader().getResource("Sample.html")).getPath()))
//                .buildEmail();
//
//        getInternalMailer().sendMail(email);
//    }
}
