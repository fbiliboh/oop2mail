import org.apache.commons.lang3.StringEscapeUtils;

import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.*;
import java.nio.charset.Charset;
import java.util.Properties;

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
    message.setFrom(new InternetAddress("dominicstefan.meier@students.bfh.ch"));

    // Set the "Subject" header field.
    message.setSubject(subject);

    // Set the "To" header field.
    message.setRecipient(javax.mail.Message.RecipientType.TO,
            new InternetAddress(recipient));
      byte[] bytechar = oop2.mailTextArea.getText().getBytes(Charset.forName("utf-8"));
      System.out.println(new String(bytechar, Charset.forName("utf-8")));
      String escutf = new String(bytechar, Charset.forName("utf-8"));
      String escjava = StringEscapeUtils.escapeJava(oop2.mailTextArea.getText());
      String eschtml = StringEscapeUtils.escapeHtml4(new String(escjava));
    // Set the given String as this part's content,
    // with a MIME type of "text/plain".
    MimeMultipart mp = new MimeMultipart();
    MimeBodyPart mbp1= new MimeBodyPart();
    String font = "courier";
    String fontsize = "2";
    String fontcolor = "#F70069";
    String htmlText = "<font size =\"" + fontsize + "\" face=\"" + font + "\" color=\"" + fontcolor + "\" ><pre>" + eschtml.replaceAll("\\\\u",";&#x").replaceAll("\\\\n", "<br>") + ";"/*oop2.mailTextArea.getText().replaceAll(">", "&gt;").replaceAll("<", "&lt;").replaceAll(">", "&amp;") */+ "</pre></font>";
    mbp1.setContent(htmlText,"text/html");
    mp.addBodyPart(mbp1);
    message.setContent(mp);

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
