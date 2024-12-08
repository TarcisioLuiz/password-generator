package com.tarcisioproject.passwordgenerator.service.imp;

import com.tarcisioproject.passwordgenerator.domain.Password;
import com.tarcisioproject.passwordgenerator.domain.dto.PasswordDto;
import com.tarcisioproject.passwordgenerator.repository.PasswordRepository;
import com.tarcisioproject.passwordgenerator.service.PasswordService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PasswordServiceImp implements PasswordService {

    private final PasswordRepository repository;
    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    private static final String UPPERCASE_CHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE_CHARS = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "!@#$%^&*()-_+=<>?";
    private static final String UPPERCASE_PATTERN = ".*[A-Z].*";
    private static final String LOWERCASE_PATTERN = ".*[a-z].*";
    private static final String NUMBER_PATTERN = ".*[0-9].*";
    private static final String SPECIAL_CHAR_PATTERN = ".*[!@#$%^&*()\\-_=+<>?].*";

    public PasswordServiceImp(PasswordRepository repository) {
        this.repository = repository;
    }

    @Override
    public Password generatePassword(PasswordDto dto) {
        Password pass = new Password();

        String password = createPassword(dto);
        password = validateAllItemns(password, dto);
        pass.setPassword(encoder.encode(password));

        repository.save(pass);

        pass.setPassword(password);

        return pass;
    }

    private String createPassword(PasswordDto dto) {
        String password;
        StringBuilder passwordBuilder = new StringBuilder();

        if (dto.isLowercase()) {
            passwordBuilder.append(getValues(dto.getPasswordLength(), LOWERCASE_CHARS));
        }
        if (dto.isUppercase()) {
            passwordBuilder.append(getValues(dto.getPasswordLength(), UPPERCASE_CHARS));
        }
        if (dto.isNumber()) {
            passwordBuilder.append(getValues(dto.getPasswordLength(), NUMBERS));
        }
        if (dto.isSpecialChar()) {
            passwordBuilder.append(getValues(dto.getPasswordLength(), SPECIAL_CHARS));
        }

        password = formatPassword(dto.getPasswordLength(), passwordBuilder);
        return password;
    }

    private String validateAllItemns(String password, PasswordDto dto) {
        if (Pattern.matches(UPPERCASE_PATTERN, password) &&
                Pattern.matches(LOWERCASE_PATTERN,  password) &&
                Pattern.matches(NUMBER_PATTERN, password) &&
                Pattern.matches(SPECIAL_CHAR_PATTERN, password)) {
            return password;
        }
        password = createPassword(dto);
        password = validateAllItemns(password, dto);
        return password;
    }

    private String formatPassword(int length, StringBuilder passwordBuilder) {
        List<Character> passwordChars = passwordBuilder.toString().chars().mapToObj(c -> (char) c).toList();
        Collections.shuffle(passwordChars);
        StringBuilder finalPassword = new StringBuilder();
        for (char c : passwordChars) {
            finalPassword.append(c);
        }
        String password = finalPassword.toString();
        return password.substring(0, length);
    }

    private String getValues(int length, String values) {
        SecureRandom random = new SecureRandom();
        StringBuilder charsBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            charsBuilder.append(values.charAt(random.nextInt(values.length())));
        }
        return charsBuilder.toString();
    }
}
