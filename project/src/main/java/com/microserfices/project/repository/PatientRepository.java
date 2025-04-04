package com.microserfices.project.repository;
import com.microserfices.project.models.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {
    Optional<Patient> findByEmail(String email);
}
