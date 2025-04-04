package com.notification_Service.notification_Service.Service;
import com.notification_Service.notification_Service.Model.Notification;
import com.notification_Service.notification_Service.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private NotificationRepository notificationRepository;

    public void sendEmail(String recipient, String subject, String message) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(recipient);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        mailSender.send(mailMessage);

        Notification notification = new Notification();
        notification.setRecipientEmail(recipient);
        notification.setMessage(message);
        notification.setTimestamp(LocalDateTime.now());

        notificationRepository.save(notification);
    }
}
