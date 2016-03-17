import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.util.Properties;

import static org.apache.commons.lang3.StringEscapeUtils.*;

/**
 * This class sends an e-mail to the BFH e-mail server
 * (hermes.bfh.ch).
 * <p>
 * Author: jean-paul.dubois@bfh.ch
 */
public class BFH_EmailSender {

  private static final String SERVER = "147.87.244.55";

  /**
   * Sends an e-mail.
   *
   * @param user      the user name of the e-mail sender (e.g. doj1)
   * @param passwd    its password
   * @param recipient the address of the e-mail recipient (e.g. doj1@bfh.ch)
   * @param subject   the e-mail subject
   * @param text      the e-mail text
   * @throws AddressException   when a wrongly formatted address is encountered
   * @throws MessagingException when the message could not be sent
   */
  public static void send(String user, char[] passwd, String recipient,
                          String subject, String text)
          throws AddressException, MessagingException {

    // Get system properties
    Properties properties = System.getProperties();

    // Account authenticator
    Authenticator authenticator = new Authenticator(user, passwd);

    // Setup mail server
    properties.setProperty("mail.smtp.host", SERVER);
    properties.setProperty("mail.smtp.auth", "true");
    properties.setProperty("mail.smtp.submitter",
            authenticator.getPasswordAuthentication().getUserName());

    // Get the default Session object.
    Session session = Session.getInstance(properties, authenticator);

    // Create a default MimeMessage object.
    MimeMessage message = new MimeMessage(session);

    // Set the RFC 822 "From" header field using the
    // value of the InternetAddress.getLocalAddress method.
    message.setFrom(new InternetAddress(UserVariables.emailbfh + "@students.bfh.ch"));

    // Set the "Subject" header field.
    message.setSubject(subject);

    // Set the "To" header field.
    message.setRecipient(javax.mail.Message.RecipientType.TO,
            new InternetAddress(recipient));
     // byte[] bytechar = oop2.editor.getHtmlText().getBytes(Charset.forName("utf-8"));
    //  System.out.println(new String(bytechar, Charset.forName("utf-8")));
    //  String escutf = new String(bytechar, Charset.forName("utf-8"));
      String escjava = escapeJava(oop2.editor.getHtmlText());
      String eschtml = escapeHtml4(new String(escjava));
   // String esc3 = escjava.replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll(">", "&amp;");
    String linesc = escjava.replaceAll("\\n", "<br>");
    // Set the given String as this part's content,
    // with a MIME type of "text/plain".
    MimeMultipart mp = new MimeMultipart();
    MimeBodyPart mbp1= new MimeBodyPart();
    String font = "Calibri";
    String fontsize = "3";
    String fontcolor = "#000";
    String htmlText = /*"<pre>" +*/ unescapeJava(linesc) /*+ "</pre>"*/;
    mbp1.setContent(htmlText, "text/html; charset=UTF-8");
    mp.addBodyPart(mbp1);
    message.setContent(mp, "text/html; charset=UTF-8");

    // Send message
    Transport.send(message);
  }

  private static class Authenticator extends javax.mail.Authenticator {

    private PasswordAuthentication authentication;

    public Authenticator(String user, char[] passwd) {
      authentication = new PasswordAuthentication(user, new String(passwd));
    }

    protected PasswordAuthentication getPasswordAuthentication() {
      return authentication;
    }
  }
} 
