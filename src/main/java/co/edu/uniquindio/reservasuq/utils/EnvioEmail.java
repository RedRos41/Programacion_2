package co.edu.uniquindio.reservasuq.utils;


import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

public class EnvioEmail {

    public static void enviarNotificacion(String destinatario, String asunto, String mensaje) {


        Email email = EmailBuilder.startingBlank()

                .from("lunalucia1234567@gmail.com")

                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        try (Mailer mailer = MailerBuilder


                //falta generar de momento el codigo ese


                .withSMTPServer("smtp.gmail.com", 587, "lunalucia1234567@gmail.com", "")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            // Enviar el email
            mailer.sendMail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
