package ru.kalimulin.springBootPasswordGenerator.services;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;

@Service
public class PasswordGeneratorService {
    private static final char[] UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

    private static final char[] LOWERCASE = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    private static final char[] DIGITS = "0123456789".toCharArray();

    private static final char[] SPECIAL_CHARACTERS = "!@#$%^&*()+=-?<>".toCharArray();

    public static String generatePassword(int length, boolean uppercase, boolean lowercase, boolean digits, boolean specialCharacters) {

        if (length < 1) {
            throw new IllegalArgumentException("Длина пароля должна быть больше 1 символа");
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();
        StringBuilder charPool = new StringBuilder();

        if (uppercase) {
            charPool.append(UPPERCASE);
        }
        if (lowercase) {
            charPool.append(LOWERCASE);
        }
        if (digits) {
            charPool.append(DIGITS);
        }
        if (specialCharacters) {
            charPool.append(SPECIAL_CHARACTERS);
        }

        char[] chars = charPool.toString().toCharArray();

        if (chars.length == 0) {
            throw new IllegalArgumentException("Необходимо выбрать хотя бы один набор символов.");
        }

        for (int i = 0; i < length; i++) {
            password.append(chars[random.nextInt(chars.length)]);
        }

        return password.toString();
    }
}
