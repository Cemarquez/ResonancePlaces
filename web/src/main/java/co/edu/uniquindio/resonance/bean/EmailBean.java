package co.edu.uniquindio.resonance.bean;

import java.io.Serializable;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


@SuppressWarnings("serial")
public class EmailBean implements Serializable {

    private static String usuario = "resonance.snc@gmail.com";
    private static String contrasenia =  "bceresonance.2629";
    private static String mensaje;
    private static String remitente;
    private static String asunto;


    //this is responsible to send email..
    public static void sendEmailBienvenida(String remitente, String datos) {

        //Variable for gmail
        String host="smtp.gmail.com";
        mensaje ="¡Te has registrado exitosamente!" + "\n\n" + datos + "\n\n"
                + "Dirigete a al ménu principal para encontrar los lugares que más te gusten." + "\n\n"
                + "Gracias por preferirnos.";

        asunto = "Bienvenido a Resonance Places!";
        String from = "resonance.snc@gmail.com";
        //get the system properties
        Properties properties = System.getProperties();

        //setting important information to properties object

        //host set
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port","465");
        properties.put("mail.smtp.ssl.enable","true");
        properties.put("mail.smtp.auth","true");

        //Step 1: to get the session object..
        Session session=Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(usuario,contrasenia);
            }



        });

        session.setDebug(true);

        //Step 2 : compose the message [text,multi media]
        MimeMessage m = new MimeMessage(session);

        try {

            //from email
            m.setFrom(from);

            //adding recipient to message
            m.addRecipient(Message.RecipientType.TO, new InternetAddress(remitente));

            //adding subject to message
            m.setSubject(asunto);


            //adding text to message
            m.setText(mensaje);

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}


