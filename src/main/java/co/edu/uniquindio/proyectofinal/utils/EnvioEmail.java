package co.edu.uniquindio.proyectofinal.utils;


import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;


public class EnvioEmail {

    public static void enviarCorreo(String destinatario, String asunto, String mensaje) {

        Email email = EmailBuilder.startingBlank()
                .from("testemailuq01@gmail.com")
                .to(destinatario)
                .withSubject(asunto)
                .withPlainText(mensaje)
                .buildEmail();

        try (Mailer mailer = MailerBuilder
                .withSMTPServer("smtp.gmail.com", 587, "testemailuq01@gmail.com", "RedRosa1")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer()) {

            mailer.sendMail(email);

        }catch (Exception e){

            e.printStackTrace();

        }

    }

}
