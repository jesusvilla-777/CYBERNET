// src/main/java/com/cybernet/controller/PingController.java
package com.example.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.security.modelo.PingService;

@Controller
public class WebController {

    @Autowired
    private PingService pingService;

    @GetMapping("/ping")
    public String mostrarFormularioPing() {
        return "ping";
    }

    @PostMapping("/ping")
    public String procesarPing(@RequestParam String host, Model model) {
        String resultado = pingService.hacerPing(host);
        model.addAttribute("resultado", resultado);
        return "ping";
    }
}
