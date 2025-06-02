import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class Main {
    public static void main(String[] args) {
        Email email = EmailBuilder
                .startingBlank()
                .from("haszysznatan@interia.pl")
                .to("kuba.mrozowski@poczta.onet.pl")
                .withSubject("Prośba")
                .withPlainText("NWW new drop")
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("poczta.interia.pl", 587, "haszysznatan@interia.pl","{haszysz420*}")
                .buildMailer() ) {
            mailer.sendMail(email);
            System.out.println("Email sent!");
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}
