package com.Appointment_Service.Appointment_Service.Controller;
import com.Appointment_Service.Appointment_Service.model.Appointment;
import com.Appointment_Service.Appointment_Service.Service.AppointmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;
    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Appointment>> getAppointmentById(@PathVariable String id) {
        return ResponseEntity.ok(appointmentService.getAppointmentById(id));
    }
    
    @GetMapping("/patient/{patientId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable String patientId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByPatient(patientId));
    }

    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable String doctorId) {
        return ResponseEntity.ok(appointmentService.getAppointmentsByDoctor(doctorId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable String id, @RequestBody Appointment appointmentDetails) {
        Appointment updatedAppointment = appointmentService.updateAppointment(id, appointmentDetails);
        return updatedAppointment != null ? ResponseEntity.ok(updatedAppointment) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable String id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
