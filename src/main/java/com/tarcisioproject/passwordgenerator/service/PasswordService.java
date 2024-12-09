package com.tarcisioproject.passwordgenerator.service;

import com.tarcisioproject.passwordgenerator.domain.Password;
import com.tarcisioproject.passwordgenerator.controller.dto.PasswordDto;

import java.util.List;

public interface PasswordService {

    public Password generatePassword(PasswordDto dto);

    public List<Password> retrievePasswords();
}
