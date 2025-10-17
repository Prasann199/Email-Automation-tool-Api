package com.email.automation.controller;


import com.email.automation.Service.EmailService;
import com.email.automation.dto.EmailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/email")
@CrossOrigin(origins = "http://localhost:5173") // React URL
public class EmailController {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<String> sendEmail(@ModelAttribute EmailRequest request) {
        try {
            emailService.sendEmails(request.getRecipients(), request.getSubject(), request.getMessage(), request.getFile());
            return ResponseEntity.ok("✅ Emails sent successfully!");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Error sending emails: " + e.getMessage());
        }
    }
}
