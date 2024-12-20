package com.tarcisioproject.passwordgenerator.controller.dto;

public class PasswordDto {

    private int passwordLength;
    private boolean uppercase;
    private boolean lowercase;
    private boolean numbers;
    private boolean specialChar;

    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public boolean isUppercase() {
        return uppercase;
    }

    public void setUppercase(boolean uppercase) {
        this.uppercase = uppercase;
    }

    public boolean isLowercase() {
        return lowercase;
    }

    public void setLowercase(boolean lowercase) {
        this.lowercase = lowercase;
    }

    public boolean isNumbers() {
        return numbers;
    }

    public void setNumbers(boolean numbers) {
        this.numbers = numbers;
    }

    public boolean isSpecialChar() {
        return specialChar;
    }

    public void setSpecialChar(boolean specialChar) {
        this.specialChar = specialChar;
    }
}
