package com.example.flight.util;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import java.io.File;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Component
public class EmailUtil {
    @Autowired
    private JavaMailSender sender;
    public void sendItinerary(String toAddress,String Filepath)
    {
        MimeMessage message=sender.createMimeMessage();
        try {
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true);
            messageHelper.setTo(toAddress);
            messageHelper.setSubject("Itinerary For your flight");
            messageHelper.setText("Please find your itinerary attached");
            messageHelper.addAttachment("Itinerary",new File(Filepath));
            sender.send(message);
        }
        catch (MessagingException e)
        {
            e.printStackTrace();

        }
    }
}
