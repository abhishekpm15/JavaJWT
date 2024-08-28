package com.example.JWT.repository;

import com.example.JWT.model.AuthModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepo extends JpaRepository<AuthModel, Integer> {
    AuthModel findByEmail(String email);
}
