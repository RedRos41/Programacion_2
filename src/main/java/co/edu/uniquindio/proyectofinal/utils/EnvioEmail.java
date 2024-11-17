package co.edu.uniquindio.proyectofinal.utils;


import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.email.EmailPopulatingBuilder;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import java.io.File;


public class EnvioEmail {

    public static void enviarCorreo(String destinatario, String asunto, String mensaje, String rutaAdjunto) {

        try {

            EmailPopulatingBuilder emailBuilder = EmailBuilder.startingBlank()
                    .from("testemailuq01@gmail.com")
                    .to(destinatario)
                    .withSubject(asunto)
                    .withPlainText(mensaje);

            if (rutaAdjunto != null && !rutaAdjunto.isEmpty()) {

                File archivo = new File(rutaAdjunto);
                if (archivo.exists()) {
                    DataSource dataSource = new FileDataSource(archivo);
                    emailBuilder.withAttachment(archivo.getName(), dataSource);

                }

            }

            Email email = emailBuilder.buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.gmail.com", 587, "testemailuq01@gmail.com", "onif vyju rssf suqb")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .withDebugLogging(true)
                    .buildMailer();

            mailer.sendMail(email);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}
