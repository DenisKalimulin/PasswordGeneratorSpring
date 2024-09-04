package ru.kalimulin.springBootPasswordGenerator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kalimulin.springBootPasswordGenerator.services.PasswordGeneratorService;

@Controller
public class PasswordController {

    private final PasswordGeneratorService passwordGeneratorService;

    public PasswordController(PasswordGeneratorService passwordGeneratorService) {
        this.passwordGeneratorService = passwordGeneratorService;
    }

    @GetMapping("/")
    public String showForm() {
        return "password";
    }

    @PostMapping("/generate")
    public String generatePassword(@RequestParam("length") int length,
                                   @RequestParam(value = "uppercase", required = false) boolean uppercase,
                                   @RequestParam(value = "lowercase", required = false) boolean lowercase,
                                   @RequestParam(value = "digits", required = false) boolean digits,
                                   @RequestParam(value = "specialCharacters", required = false) boolean specialCharacters,
                                   Model model) {
        String password = passwordGeneratorService.generatePassword(length, uppercase, lowercase, digits, specialCharacters);
        model.addAttribute("password", password);

        return "password";
    }
}
