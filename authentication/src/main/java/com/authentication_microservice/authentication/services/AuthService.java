package com.authentication_microservice.authentication.services;

import com.authentication_microservice.authentication.model.User;
import com.authentication_microservice.authentication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(AuthenticationManager authenticationManager, JwtEncoder jwtEncoder, 
                       UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Map<String, String> login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        User user = optionalUser.get();

        // ✅ Verify password correctly
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Incorrect password");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        Instant instant = Instant.now();

        // ✅ Ensure roles are handled properly
        String scope = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet jwtClaimsSet = JwtClaimsSet.builder()
                .issuedAt(instant)
                .expiresAt(instant.plus(10, ChronoUnit.MINUTES))
                .subject(email)
                .claim("scope", scope.isEmpty() ? "USER" : scope) // Default to "USER" if empty
                .build();

        JwtEncoderParameters jwtEncoderParameters = JwtEncoderParameters.from(
                JwsHeader.with(MacAlgorithm.HS512).build(),
                jwtClaimsSet
        );

        String jwt = jwtEncoder.encode(jwtEncoderParameters).getTokenValue();

        return Map.of(
                "message", "Login successful",
                "access-token", jwt
        );
    }

    public Map<String, Object> register(String fullName, String email, String password) {

        if (userRepository.findByEmail(email).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        // ✅ Ensure `role` is stored as a list
        User user = User.builder()
                .email(email)
                .fullName(fullName)
                .password(encodedPassword)
                .role(("USER")) 
                .build();

        User savedUser = userRepository.save(user);

        return Map.of("message", "Registration successful", "user", savedUser);
    }
}
