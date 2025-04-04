package com.notification_Service.notification_Service.Repository;
import com.notification_Service.notification_Service.Model.Notification;

import org.springframework.data.mongodb.repository.MongoRepository;



public interface NotificationRepository extends MongoRepository<Notification, String> {
    
}

