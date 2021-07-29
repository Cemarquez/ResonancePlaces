package co.edu.uniquindio.resonance.bean;

import org.primefaces.util.HTML;

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
    private static String NUMEROS = "0123456789";
    private static String MAYUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";


    public static void sendEmailBienvenida(String remitente, String datos) {

        //Variable for gmail
        String host="smtp.gmail.com";
        mensaje ="<table style=\"max-width: 600px; padding: 10px; margin: 0 auto; border-collapse: collapse;\">\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1; text-align: left; padding: 0\">\n" +
                "        <a href=\"https://github.com/Cemarquez/ResonancePlaces\">\n" +
                "          <img width=\"20%\" style=\"display:block; margin: 1.5% 3%;\" src=\"https://i.postimg.cc/GppXfxD7/Resonance-Places.png\">\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td style=\"padding: 0\">\n" +
                "        <img style=\"padding: 0; display: block\" src=\"https://i.postimg.cc/G26rt2rR/turimos.jpg\" width=\"100%\">\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1\">\n" +
                "        <div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif\">\n" +
                "          <h2 style=\"color: #e67e22; margin: 0 0 7px\">¡Hola "+ datos +"!</h2>\n" +
                "          <p style=\"margin: 2px; font-size: 15px\">\n" +
                "            Somos una empresa que incentiva el comercio y la gastronomía regional, donde se busca apoyar a\n" +
                "            los pequeños comerciantes.<br>\n" +
                "            Entre todos los comerciantes pequeños valoramos y apoyamos las siguientes categorías:</p>\n" +
                "          <ul style=\"font-size: 15px;  margin: 10px 0\">\n" +
                "            <li>Restaurantes.</li>\n" +
                "            <li>Hoteles.</li>\n" +
                "            <li>Centros de entretenimiento.</li>\n" +
                "            <li>Bares.</li>\n" +
                "            <li>Discotecas.</li>\n" +
                "            <li>Muchas categorías más.</li>\n" +
                "          </ul>\n" +
                "          <div style=\"width: 100%;margin:20px 0; display: inline-block;text-align: center\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/ry9QqH9n/1.jpg\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/25v7M96x/2.jpg\">\n" +
                "          </div>\n" +
                "          <div style=\"width: 100%; text-align: center\">\n" +
                "            <a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"https://github.com/Cemarquez/ResonancePlaces\">Ir a la página</a>\n" +
                "          </div>\n" +
                "          <p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Resonance Places 2021</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "</table>";
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
            m.setContent(mensaje, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendEmailContraseña (String remitente, String datos) {

        //Variable for gmail
        String host="smtp.gmail.com";
        mensaje ="<table style=\"max-width: 600px; padding: 10px; margin: 0 auto; border-collapse: collapse;\">\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1; text-align: left; padding: 0\">\n" +
                "        <a href=\"https://github.com/Cemarquez/ResonancePlaces\">\n" +
                "          <img width=\"20%\" style=\"display:block; margin: 1.5% 3%;\" src=\"https://i.postimg.cc/GppXfxD7/Resonance-Places.png\">\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1\">\n" +
                "        <div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif\">\n" +
                "          <h2 style=\"color: #e67e22; margin: 0 0 7px\">¡Recuperación de contraseña exitosa!</h2>\n" +
                "          <p style=\"margin: 2px; font-size: 15px\">\n" +
                "            Te generamos una contraseña temporal, con la cual podras ingresar a tu cuenta y poder cambiar a una contraseña de tu preferencia.\n" +
                "            <br><br>\n" +
                "            Contraseña temporal:</p>\n" +
                "          <br>\n" +
                "          <h3 style=\"color: #e67e22; margin: 0 0 7px\">"+datos+"</h3>\n" +
                "          <div style=\"width: 100%;margin:20px 0; display: inline-block;text-align: center\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/ry9QqH9n/1.jpg\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/25v7M96x/2.jpg\">\n" +
                "          </div>\n" +
                "          <div style=\"width: 100%; text-align: center\">\n" +
                "            <a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"https://github.com/Cemarquez/ResonancePlaces\">Ir a la página</a>\n" +
                "          </div>\n" +
                "          <p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Resonance Places 2021</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "</table>";

        asunto = "Recuperación de contraseña - Resonance Places";
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
            m.setContent(mensaje, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendEmailComentario (String remitente, String user, String comentario, String tituloComentario, String lugar) {

        //Variable for gmail
        String host="smtp.gmail.com";
        mensaje ="<table style=\"max-width: 600px; padding: 10px; margin: 0 auto; border-collapse: collapse;\">\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1; text-align: left; padding: 0\">\n" +
                "        <a href=\"https://github.com/Cemarquez/ResonancePlaces\">\n" +
                "          <img width=\"20%\" style=\"display:block; margin: 1.5% 3%;\" src=\"https://i.postimg.cc/GppXfxD7/Resonance-Places.png\">\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1\">\n" +
                "        <div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif\">\n" +
                "          <h2 style=\"color: #e67e22; margin: 0 0 7px\">¡El usuario "+ user +" a comentado!</h2>\n" +
                "            Comentario para el lugar :</p>\n" +
                "          <br>\n" +
                "          <h3 style=\"color: #e67e22; margin: 0 0 7px\">"+lugar+"</h3>\n" +
                "            Comentario:</p>\n" +
                "          <br>\n" +
                "          <h4 style=\"color: #e67e22; margin: 0 0 7px\">"+ tituloComentario + ": "+comentario+"</h4>\n" +
                "          <div style=\"width: 100%;margin:20px 0; display: inline-block;text-align: center\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/ry9QqH9n/1.jpg\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/25v7M96x/2.jpg\">\n" +
                "          </div>\n" +
                "          <div style=\"width: 100%; text-align: center\">\n" +
                "            <a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"https://github.com/Cemarquez/ResonancePlaces\">Ir a la página</a>\n" +
                "          </div>\n" +
                "          <p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Resonance Places 2021</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "</table>";

        asunto = "Comentario de " + user;
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
            m.setContent(mensaje, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void sendEmailRespuesta (String remitente, String user, String comentario, String tituloComentario, String lugar, String respuesta) {

        //Variable for gmail
        String host="smtp.gmail.com";
        mensaje ="<table style=\"max-width: 600px; padding: 10px; margin: 0 auto; border-collapse: collapse;\">\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1; text-align: left; padding: 0\">\n" +
                "        <a href=\"https://github.com/Cemarquez/ResonancePlaces\">\n" +
                "          <img width=\"20%\" style=\"display:block; margin: 1.5% 3%;\" src=\"https://i.postimg.cc/GppXfxD7/Resonance-Places.png\">\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1\">\n" +
                "        <div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif\">\n" +
                "          <h2 style=\"color: #e67e22; margin: 0 0 7px\">¡El usuario "+ user +" del lugar "+lugar+" te ha respondido!</h2>\n" +
                "            Comentario:</p>\n" +
                "          <br>\n" +
                "          <h4 style=\"color: #e67e22; margin: 0 0 7px\">"+ tituloComentario + ": "+comentario+"</h4>\n" +
                "            Respuesta:</p>\n" +
                "          <br>\n" +
                "          <h4 style=\"color: #e67e22; margin: 0 0 7px\">"+respuesta+"</h4>\n" +
                "          <div style=\"width: 100%;margin:20px 0; display: inline-block;text-align: center\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/ry9QqH9n/1.jpg\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/25v7M96x/2.jpg\">\n" +
                "          </div>\n" +
                "          <div style=\"width: 100%; text-align: center\">\n" +
                "            <a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"https://github.com/Cemarquez/ResonancePlaces\">Ir a la página</a>\n" +
                "          </div>\n" +
                "          <p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Resonance Places 2021</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "</table>";

        asunto = "Respuesta de " + user;
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
            m.setContent(mensaje, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void sendEmailContacto (String remitente, String user, String comentario, String tituloComentario, String lugar, String correoUsuario) {

        //Variable for gmail
        String host="smtp.gmail.com";
        mensaje ="<table style=\"max-width: 600px; padding: 10px; margin: 0 auto; border-collapse: collapse;\">\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1; text-align: left; padding: 0\">\n" +
                "        <a href=\"https://github.com/Cemarquez/ResonancePlaces\">\n" +
                "          <img width=\"20%\" style=\"display:block; margin: 1.5% 3%;\" src=\"https://i.postimg.cc/GppXfxD7/Resonance-Places.png\">\n" +
                "        </a>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "\n" +
                "    <tr>\n" +
                "      <td style=\"background-color: #ecf0f1\">\n" +
                "        <div style=\"color: #34495e; margin: 4% 10% 2%; text-align: justify;font-family: sans-serif\">\n" +
                "          <h2 style=\"color: #e67e22; margin: 0 0 7px\">¡El usuario "+ user +" te ha contactado!</h2>\n" +
                "            Mensaje para el lugar :<h3 style=\\\"color: #e67e22; margin: 0 0 7px\\\">"+lugar+"</h3></p>\n" +
                "          \n" +
                "          \n" +
                "          <br>\n" +
                "          <h4 style=\"color: #e67e22; margin: 0 0 7px\">"+ "Asunto: "+ "<strong>"+ tituloComentario  + "</strong>" +"</h4>\n" +
                "          <br>\n" +
                "          <h4 style=\"color: #e67e22; margin: 0 0 7px\">"+comentario+"</h4>\n" +
                "            Respondele al correo :<h3 style=\\\"color: #e67e22; margin: 0 0 7px\\\">"+correoUsuario+"</h3></p>\n" +

                "          <div style=\"width: 100%;margin:20px 0; display: inline-block;text-align: center\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/ry9QqH9n/1.jpg\">\n" +
                "            <img style=\"padding: 0; width: 200px; margin: 5px\" src=\"https://i.postimg.cc/25v7M96x/2.jpg\">\n" +
                "          </div>\n" +
                "          <div style=\"width: 100%; text-align: center\">\n" +
                "            <a style=\"text-decoration: none; border-radius: 5px; padding: 11px 23px; color: white; background-color: #3498db\" href=\"https://github.com/Cemarquez/ResonancePlaces\">Ir a la página</a>\n" +
                "          </div>\n" +
                "          <p style=\"color: #b3b3b3; font-size: 12px; text-align: center;margin: 30px 0 0\">Resonance Places 2021</p>\n" +
                "        </div>\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "</table>";

        asunto = tituloComentario;
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
            m.setContent(mensaje, "text/html");

            //send

            //Step 3 : send the message using Transport class
            Transport.send(m);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String getPinNumber() {
        return getPassword(NUMEROS, 4);
    }

    public static String getPassword() {
        return getPassword(8);
    }

    public static String getPassword(int length) {
        return getPassword(NUMEROS + MAYUSCULAS + MINUSCULAS, length);
    }

    public static String getPassword(String key, int length) {
        String pswd = "";

        for (int i = 0; i < length; i++) {
            pswd+=(key.charAt((int)(Math.random() * key.length())));
        }

        return pswd;
    }
}


