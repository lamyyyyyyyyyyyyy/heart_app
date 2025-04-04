package com.microserfices.project.Controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.RequiredArgsConstructor;

import com.microserfices.project.models.Patient;
import com.microserfices.project.service.PatientService;

@RestController
@RequestMapping("/api/patients")  // Fixed naming convention
@RequiredArgsConstructor  // No need for @Autowired
public class PatientController {
    
    private final PatientService patientService;  // Spring injects automatically

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.createPatient(patient));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
