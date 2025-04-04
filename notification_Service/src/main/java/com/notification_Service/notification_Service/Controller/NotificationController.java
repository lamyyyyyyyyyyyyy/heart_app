package com.notification_Service.notification_Service.Controller;

import com.notification_Service.notification_Service.Service.EmailService;
import org.springframework.web.bind.annotation.*;



import java.util.Map;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public String sendEmail(@RequestBody Map<String, String> request) {
        String recipient = request.get("recipient");
        String message = request.get("message");

        emailService.sendEmail(recipient, "Notification", message);
        return "Email sent successfully!";
    }
}
