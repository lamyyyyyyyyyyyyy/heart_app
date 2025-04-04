package com.authentication_microservice.authentication.repository;

import org.springframework.stereotype.Repository;
import com.authentication_microservice.authentication.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(String email);
}