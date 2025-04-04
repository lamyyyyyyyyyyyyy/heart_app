package com.microserfices.project.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "patients") // MongoDB Collection
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


public class Patient {
    @Id
    private String id;
    private String name;
    private String email;
    private LocalDate dateOfBirth;
    private String gender;
    private String adress;
    private String phoneNumber;

    //Fields for Medical History
    private String bloodType;
    private List<String> allergies;
    private List<String> medications;
    private List<String> previousConditions;

}
