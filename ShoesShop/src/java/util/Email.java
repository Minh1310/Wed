/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import jakarta.servlet.http.HttpServletRequest;

/**
 *
 * @author Admin
 */
public class Email {

    static final String from = "buianhtu293@gmail.com";
    static final String password = "nuucsqxswbwhzcjv";

    public static String getRandomNumber() {
        Random rd = new Random();
        String s1 = rd.nextInt(10) + "";
        String s2 = rd.nextInt(10) + "";
        String s3 = rd.nextInt(10) + "";
        String s4 = rd.nextInt(10) + "";
        String s5 = rd.nextInt(10) + "";
        String s6 = rd.nextInt(10) + "";

        String s = s1 + s2 + s3 + s4 + s5 + s6;
        return s;
    }

    public static boolean sendEmail(String to, String tieuDe, String noiDung, String code) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(/*from*/);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject(tieuDe);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))
            // Nội dung
            msg.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Verify Your Email</title>\n"
                    + "</head>\n"
                    + "<body style=\"margin: 0; padding: 0; background-color: #f4f4f4; font-family: Arial, sans-serif;\">\n"
                    + "    <center>\n"
                    + "        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f4f4f4\">\n"
                    + "            <tr>\n"
                    + "                <td style=\"padding: 30px;\">\n"
                    + "                    <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#ffffff\" style=\"margin: auto; background: #ffffff; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\">\n"
                    + "                        <tr>\n"
                    + "                            <td style=\"padding: 40px; text-align: center; background-color: #0056b3; color: #ffffff;\">\n"
                    + "                                <h1>Welcome to Our Service!</h1>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                        <tr>\n"
                    + "                            <td style=\"padding: 20px 40px 40px; text-align: center; font-size: 16px; color: #555;\">\n"
                    + "                                <p>Thanks for signing up! Please confirm your email address by clicking the button below.</p>\n"
                    + "                                <!-- Button Start -->\n"
                    + "                                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "                                    <tr>\n"
                    + "                                        <td style=\"padding: 30px; text-align: center;\">\n"
                    + "                                            <a href=\"" + noiDung + "?email=" + to + "&code="+ code + "\" target=\"_blank\" style=\"font-size: 16px; background-color: #22a8ff; color: #ffffff; padding: 12px 30px; text-decoration: none; border-radius: 5px; border: 1px solid #1a87d6; display: inline-block;\">\n"
                    + "                                                Verify Email\n"
                    + "                                            </a>\n"
                    + "                                        </td>\n"
                    + "                                    </tr>\n"
                    + "                                </table>\n"
                    + "                                <!-- Button End -->\n"
                    + "                                <p style=\"color: #999; font-size: 14px; margin-top: 20px;\">If you did not create an account, no further action is required.</p>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </table>\n"
                    + "    </center>\n"
                    + "</body>\n"
                    + "</html>", "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }
    
    public static boolean sendEmail2(String to, String tieuDe, String noiDung, String code, long expirationTimeMillis) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject(tieuDe);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))
            // Nội dung
            msg.setContent("<!DOCTYPE html>\n"
                    + "<html lang=\"en\">\n"
                    + "<head>\n"
                    + "    <meta charset=\"UTF-8\">\n"
                    + "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
                    + "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
                    + "    <title>Verify Your Email</title>\n"
                    + "</head>\n"
                    + "<body style=\"margin: 0; padding: 0; background-color: #f4f4f4; font-family: Arial, sans-serif;\">\n"
                    + "    <center>\n"
                    + "        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#f4f4f4\">\n"
                    + "            <tr>\n"
                    + "                <td style=\"padding: 30px;\">\n"
                    + "                    <table width=\"600\" cellpadding=\"0\" cellspacing=\"0\" bgcolor=\"#ffffff\" style=\"margin: auto; background: #ffffff; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);\">\n"
                    + "                        <tr>\n"
                    + "                            <td style=\"padding: 40px; text-align: center; background-color: #0056b3; color: #ffffff;\">\n"
                    + "                                <h1>Welcome to Our Service!</h1>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                        <tr>\n"
                    + "                            <td style=\"padding: 20px 40px 40px; text-align: center; font-size: 16px; color: #555;\">\n"
                    + "                                <p>You forgot your password! Please confirm your email address by clicking the button below.</p>\n"
                    + "                                <!-- Button Start -->\n"
                    + "                                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">\n"
                    + "                                    <tr>\n"
                    + "                                        <td style=\"padding: 30px; text-align: center;\">\n"
                    + "                                            <a href=\"" + noiDung + "?email=" + to + "&code="+ code + "&expirationTimeMillis=" + expirationTimeMillis + "\" target=\"_blank\" style=\"font-size: 16px; background-color: #22a8ff; color: #ffffff; padding: 12px 30px; text-decoration: none; border-radius: 5px; border: 1px solid #1a87d6; display: inline-block;\">\n"
                    + "                                                Reset Password\n"
                    + "                                            </a>\n"
                    + "                                        </td>\n"
                    + "                                    </tr>\n"
                    + "                                </table>\n"
                    + "                                <!-- Button End -->\n"
                    + "                                <p style=\"color: #999; font-size: 14px; margin-top: 20px;\">If you did not click in 60 second, this link will cancel after 60 second.</p>\n"
                    + "                            </td>\n"
                    + "                        </tr>\n"
                    + "                    </table>\n"
                    + "                </td>\n"
                    + "            </tr>\n"
                    + "        </table>\n"
                    + "    </center>\n"
                    + "</body>\n"
                    + "</html>", "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public static void main(String[] args) {
//        Email.sendEmail("buianhtu1112@gmail.com", "Enter This Code To Verify Your Email", "Security Code: ");
        System.out.println(getRandomNumber());
    }
}
