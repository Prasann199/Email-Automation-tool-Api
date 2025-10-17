package com.email.automation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.mail.internet.MimeMessage;
import java.util.List;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendEmails(List<String> recipients, String subject, String message, MultipartFile file) throws Exception {
        for (String to : recipients) {
            sendEmailWithAttachment(to.trim(), subject, message, file);
        }
    }

    private void sendEmailWithAttachment(String to, String subject, String message, MultipartFile file) throws Exception {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(message, true);

        if (file != null && !file.isEmpty()) {
            helper.addAttachment(file.getOriginalFilename(), new ByteArrayResource(file.getBytes()));
        }

        mailSender.send(mimeMessage);
    }
}
