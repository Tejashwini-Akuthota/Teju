package com.New.LHS20.Service;

import java.util.Random;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

import com.New.LHS20.Dao.RegistrationRepository;
import com.New.LHS20.payload.ForgotPassword;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailServiceImpl implements MailService {
    int i;
    @Autowired
    JavaMailSender javaMailSender;

    @Autowired
    SimpleMailMessage mailMessage;

    @Autowired
    HttpSession session;

    @Autowired
    RegistrationRepository regrepo;
    private String otp;
    public String to;

    
    public void sendMail(String to) {


        Random r = new Random();

        i = r.nextInt(9999);
        if (i < 1000) {
            i += 100;
        }

        System.out.println(i);
        try {

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");

            message.setFrom(new InternetAddress("lifelinehealthcare20o@gmail.com"));
            message.setTo(to);
            message.setSubject("Password Reset for LHS");
            message.setText("<html> <h2> <font color=#6f42c1>"
                    + " Please enter below OTP to reset your Life Line Health Care account password </h2>"
                    + "<h2 style=\"color:MediumSeaGreen;\">  " + i + "</h2>"
                    + " <h2 style=\"background-color:hsl(9, 100%, 64%);\"> Note:This otp is valid for the next 2 hours</h2></html> ",
                    true);
            // Send message
            javaMailSender.send(mimeMessage);
            System.out.println("message sent successfully....");

             String otp = String.valueOf(i);
            //this.session.setAttribute("otp",this.otp);
            //this.session.setAttribute("to", to);

              this.to=to;

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ResponseEntity forgotPassword(ForgotPassword forgotPasscode) {

        if (regrepo.existsByEmail(forgotPasscode.getEmail())) {
            sendMail(forgotPasscode.getEmail());
            return new ResponseEntity("Otp sent",HttpStatus.OK);
        } else {
            return new ResponseEntity("email doesn't exist", HttpStatus.BAD_REQUEST);
        }

    }
}