package com.tarcisioproject.passwordgenerator.service;

import com.tarcisioproject.passwordgenerator.domain.Password;
import com.tarcisioproject.passwordgenerator.domain.dto.PasswordDto;

public interface PasswordService {

    public Password generatePassword (PasswordDto dto);
}
