package com.example.security.controller;

import com.example.security.modelo.Usuario;
import com.example.security.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Cambiado de "/registrar" a "/registro"
    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro"; // Muestra el formulario registro.html
    }

    // Cambiado de "/registrar" a "/registro"
    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String username,
                                   @RequestParam String email,
                                   @RequestParam String password,
                                   @RequestParam String confirmPassword,
                                   Model model) {
        try {
            if (usuarioRepository.existsByUsername(username)) {
                throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
            }

            if (usuarioRepository.existsByEmail(email)) {
                throw new IllegalArgumentException("El correo electrónico ya está en uso.");
            }

            if (!password.equals(confirmPassword)) {
                throw new IllegalArgumentException("Las contraseñas no coinciden.");
            }

            // Crear nuevo usuario
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setUsername(username);
            nuevoUsuario.setEmail(email);
            nuevoUsuario.setPassword(passwordEncoder.encode(password));
            nuevoUsuario.setNombre("Nombre");
            nuevoUsuario.setApellido("Apellido");
            nuevoUsuario.setActivo(true);

            usuarioRepository.save(nuevoUsuario);

            model.addAttribute("success", "Registro exitoso. Ahora puedes iniciar sesión.");
            return "registro";
        } catch (IllegalArgumentException e) {
            model.addAttribute("error", e.getMessage());
            return "registro";
        }
    }
}
