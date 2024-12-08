package com.tarcisioproject.passwordgenerator.controller;

import com.tarcisioproject.passwordgenerator.domain.Password;
import com.tarcisioproject.passwordgenerator.domain.dto.PasswordDto;
import com.tarcisioproject.passwordgenerator.service.PasswordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PasswordController {

    private PasswordService service;

    public PasswordController(PasswordService service) {
        this.service = service;
    }

    @PostMapping("generate-password")
    public ResponseEntity<Password> generatePassword(@RequestBody PasswordDto dto) {
        Password response = service.generatePassword(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
