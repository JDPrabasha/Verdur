package Manager.Restock;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendSupplierMail {
    private String supplierEmail;
    private String ingredient;
    private int quantity;
    private String restockDate;

    public SendSupplierMail(String supplierEmail, String ingredient, int quantity, String restockDate) {
        this.supplierEmail = supplierEmail;
        this.ingredient = ingredient;
        this.quantity = quantity;
        this.restockDate = restockDate;
    }

    public SendSupplierMail(String supplierEmail, String ingredient) {
        this.supplierEmail = supplierEmail;
        this.ingredient = ingredient;
    }

    public void sendMail(){
        String to = this.supplierEmail;
        String from = "verdurtest@gmail.com";

        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");


        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication("verdurtest@gmail.com", "chilloutwegottime");

            }

        });

        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set Subject: header field
            message.setSubject("New Restock Request");


            message.setText("A Restock Request has been made on item : " + this.ingredient + ". Login via your account to view more details.");
//            message.setText("Click Here ::");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }


    }
}
