package framework.mailer;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Sending Email");
        EMailWriter writer = EMailWriter.writeNewEMail();
        writer.configureEmail()
                .to("Rusty Young", "ryoung@idfbins.com")
                .to("Ken Tennant", "ktennant@idfbins.com")
                .cc("Anurag Deshpande","adeshpande@idfbins.com")
                .withSubject("Automated EMail Test")
                .withHTMLText("<h1>This is a test email, please ignore<h1><br><br>---- A hard working Robot");
        writer.sendEMail();

        System.out.println("EMail Sent");
    }
}
