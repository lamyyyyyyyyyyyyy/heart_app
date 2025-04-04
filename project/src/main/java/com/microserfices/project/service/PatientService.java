package com.microserfices.project.service;
import com.microserfices.project.models.Patient;
import com.microserfices.project.repository.PatientRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

@Service

public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    public List<Patient> getAllPatients(){
      return patientRepository.findAll();
    }

    public Optional<Patient> getPatientById(String id) {
       return patientRepository.findById(id);
    } 
    public Optional<Patient> getPatientByEmail(String Email) {
       return patientRepository.findByEmail(Email);
    }
    public Patient createPatient (Patient patient){
        return patientRepository.save(patient);
    }
    public void deletePatient(String id){
        patientRepository.deleteById(id);
    }
}


