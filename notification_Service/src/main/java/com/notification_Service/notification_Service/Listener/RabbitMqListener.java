package com.notification_Service.notification_Service.Listener;

import com.notification_Service.notification_Service.Service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class RabbitMqListener {
    private final EmailService emailService;
    public RabbitMqListener(EmailService emailService) {
        this.emailService = emailService;
    }
    @RabbitListener(queues = "notificationQueue")
    public void handleNotification(Map<String, String> message) {
        String recipient = message.get("recipient");
        String content = message.get("message");

        emailService.sendEmail(recipient, "Notification", content);
    }
    
}
