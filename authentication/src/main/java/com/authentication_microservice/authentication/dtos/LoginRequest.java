package com.authentication_microservice.authentication.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    private String email;
    private String password;

}