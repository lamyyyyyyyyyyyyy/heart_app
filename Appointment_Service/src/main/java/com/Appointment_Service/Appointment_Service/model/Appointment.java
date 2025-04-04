package com.Appointment_Service.Appointment_Service.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;


@Data
@Document(collection = "appointments")
public class Appointment {
    @Id
    private String id;
    private String patientId;
    private String doctorId;
    private LocalDateTime dateTime;
    private String status;  // PENDING, CONFIRMED, CANCELED
}