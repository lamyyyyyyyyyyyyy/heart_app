package com.notification_Service.notification_Service.Model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.time.LocalDateTime;

@Data
@Document(collection = "notifications")
public class Notification {
    @MongoId
    private String id;
    private String recipientEmail;
    private String message;
    private LocalDateTime timestamp;
}
