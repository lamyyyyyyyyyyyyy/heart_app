package com.Appointment_Service.Appointment_Service.Service;
import com.Appointment_Service.Appointment_Service.model.Appointment;
import com.Appointment_Service.Appointment_Service.Repository.AppointmentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }
    public Appointment createAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAppointmentsByPatient(String patientId) {
        return appointmentRepository.findByPatientId(patientId);
    }
    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }
    public Optional<Appointment> getAppointmentById(String id) {
        return appointmentRepository.findById(id);
    }
    public Appointment updateAppointment(String id, Appointment appointmentDetails) {
        return appointmentRepository.findById(id).map(appointment -> {
            appointment.setDateTime(appointmentDetails.getDateTime());
            appointment.setStatus(appointmentDetails.getStatus());
            return appointmentRepository.save(appointment);
        }).orElse(null);
    }

    public void deleteAppointment(String id) {
        appointmentRepository.deleteById(id);
    }
}
