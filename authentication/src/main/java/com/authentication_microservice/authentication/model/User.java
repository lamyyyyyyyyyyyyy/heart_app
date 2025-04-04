package com.authentication_microservice.authentication.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    @Id
    private String id; // MongoDB uses String ID

    private String fullName;

    private String email; // No need for @Column in MongoDB

    private String password;

    private String username;

    private String role;
    
    public String getRole() {
        return role;
    }
}

