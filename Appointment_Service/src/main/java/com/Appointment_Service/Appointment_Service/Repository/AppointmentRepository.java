package com.Appointment_Service.Appointment_Service.Repository;
import com.Appointment_Service.Appointment_Service.model.Appointment;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {
    List<Appointment> findByPatientId(String patientId);
    List<Appointment> findByDoctorId(String doctorId);
}