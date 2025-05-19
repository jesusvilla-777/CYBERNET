package com.example.security.controller;

import com.example.security.dto.UsuarioDTO;
import com.example.security.servicios.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/")
    public String indexPage(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            boolean isAdmin = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_ADMIN"));

            boolean isUser = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .anyMatch(role -> role.equals("ROLE_USER"));

            if (isAdmin) {
                return "redirect:/admin";
            } else if (isUser) {
                return "redirect:/user";
            }
        }
        return "redirect:/home";
    }

    @GetMapping("/home")
    public String homePage() {
        return "home";
    }

    @GetMapping("/admin")
    public String admin() {
        return "admin";
    }

    @GetMapping("/user")
    public String user() {
        return "user";
    }

    @GetMapping("/registrar")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userForm", new UsuarioDTO());
        return "registrar";
    }

    @PostMapping("/registrar")
    public String registerUser(@ModelAttribute("userForm") UsuarioDTO userForm, Model model) {
        if (userService.existsByUsername(userForm.getUsername())) {
            model.addAttribute("errorMessage", "El nombre de usuario ya existe.");
            return "registrar";
        }

        if (!userForm.getPassword().equals(userForm.getConfirmPassword())) {
            model.addAttribute("errorMessage", "Las contraseñas no coinciden.");
            return "registrar";
        }

        try {
            userService.save(userForm);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "registrar";
        }

        return "redirect:/registrar?success";
    }
}
