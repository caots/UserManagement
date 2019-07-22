package com.bklsoftwarevn.service_impl;

import com.bklsoftwarevn.common.MD5;
import com.bklsoftwarevn.entities.user.UserMail;
import com.bklsoftwarevn.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class SendMailService_Impl implements SendMailService {

    private static final Logger LOGGER = Logger.getLogger(SendMailService_Impl.class.getName());

    /*
     * Trên thực tế, Java cung cấp hai lớp để gửi thư. Nếu bạn muốn gửi thư đơn giản mà không có tệp đính kèm thì đối
     * tượng của SimpleMailMessage được sử dụng
     * nếu không sử dụng Đối tượng của MimeMessage .
     */

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    String emailAdminAddress;


    @Override
    public boolean sendEMail(String email) {
        try {

            UserMail userMail = userSendMail(email);
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(emailAdminAddress);
            mail.setTo(userMail.getEmailAddress());
            mail.setSubject(userMail.getTitle());
            mail.setText(userMail.getContent());
            javaMailSender.send(mail);
            return true;

        } catch (MailException ex) {
            LOGGER.log(Level.SEVERE, "send-mail-error : {0}", ex.getMessage());
        }
        return false;
    }

    public UserMail userSendMail(String email) {

        String content = "Mã xác thực: " + generateCode();
        UserMail userMail = new UserMail();
        userMail.setEmailAddress(email);
        userMail.setTitle("BkSoftwarevn thân gửi");
        userMail.setContent(content);

        return userMail;
    }

    public String generateCode() {
        Random random = new Random();
        int numberOne = random.nextInt();
        int numberTwo = random.nextInt();
        int numberThree = random.nextInt();
        int numberFour = random.nextInt();
        return MD5.encode(numberOne + "C" + numberTwo + "A" + numberThree + "O" + numberFour);
    }


    //Gửi mail với tệp đính kèm
/*
    public void sendEmailWithAttachment(UserMail user) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setTo(user.getEmailAddress());
            helper.setSubject("Testing Mail API with Attachment");
            helper.setText("Please find the attached document below.");
            FileSystemResource file = new FileSystemResource("/home/caots/Desktop/abc.pdf");
            helper.addAttachment(file.getFilename(), file);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException ex) {
            LOGGER.log(Level.SEVERE, "send-mail-with-attachment-error : {0}", ex.getMessage());
        }
    }
*/
}

/*
<!DOCTYPE html>\n" +
        "<html lang=\"en\">\n" +
        "<head>\n" +
        "  <title>Bootstrap Example</title>\n" +
        "  <meta charset=\"utf-8\">\n" +
        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
        "  <link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css\">\n" +
        "  <script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js\"></script>\n" +
        "  <script src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js\"></script>\n" +
        "  <script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js\"></script>\n" +
        "</head>\n" +
        "<body>\n" +
        "\n" +
        "<div class=\"container\">\n" +
        "  <div class=\"alert alert-info\">\n" +
        "    <strong>Info!</strong> You should <a href=\"#\" class=\"alert-link\">read this message</a>.\n" +
        "  </div>\n" +
        "</div>\n" +
        "</body>\n" +
        "</html>"*/
