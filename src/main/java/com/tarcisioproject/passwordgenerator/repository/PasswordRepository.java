package com.tarcisioproject.passwordgenerator.repository;

import com.tarcisioproject.passwordgenerator.domain.Password;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PasswordRepository extends JpaRepository<Password, UUID> {
}
