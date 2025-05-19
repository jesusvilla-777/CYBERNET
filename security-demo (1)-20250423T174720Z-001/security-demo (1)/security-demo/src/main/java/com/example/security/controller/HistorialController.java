package com.example.security.controller;

import com.example.security.modelo.Evento;
import com.example.security.servicios.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HistorialController {

    @Autowired
    private EventoService eventoService;

    @GetMapping("/admin/historial")
    public String mostrarHistorial(Model model) {
        List<Evento> eventos = eventoService.findAll();
        model.addAttribute("eventos", eventos);
        return "admin/historial"; // archivo: src/main/resources/templates/admin/historial.html
    }
}
