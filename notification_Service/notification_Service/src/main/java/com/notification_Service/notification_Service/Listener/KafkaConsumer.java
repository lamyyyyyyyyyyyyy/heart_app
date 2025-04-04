package com.notification_Service.notification_Service.Listener;


import com.notification_Service.notification_Service.Service.EmailService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import java.util.Map;


@Service
public class KafkaConsumer {
    private final EmailService emailService;

    public KafkaConsumer(EmailService emailService) {
        this.emailService = emailService;
    }
    @KafkaListener(topics = "notificationTopic", groupId = "notification-group")
    public void consume(ConsumerRecord<String, Map<String, String>> record) {
        Map<String, String> message = record.value();
        String recipient = message.get("recipient");
        String content = message.get("message");

        emailService.sendEmail(recipient, "Notification", content);
    }
}
