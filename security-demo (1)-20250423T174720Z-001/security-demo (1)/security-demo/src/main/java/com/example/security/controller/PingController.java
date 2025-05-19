package com.example.security.controller;

import com.example.security.modelo.Evento;
import com.example.security.servicios.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.net.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class PingController {

    private static final int TIMEOUT = 3000;

    @Autowired
    private EventoService eventoService;

    @PostMapping("/ping")
    public ResponseEntity<Map<String, Object>> hacerPing(@RequestBody Map<String, Object> payload) {
        String ip = (String) payload.get("ip");
        Map<String, Object> response = new HashMap<>();

        if (ip == null || ip.isEmpty()) {
            response.put("message", "⚠️ La dirección IP no puede estar vacía.");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean exitoso = false;
        String descripcion;

        try {
            InetAddress inet = InetAddress.getByName(ip);
            boolean reachable = inet.isReachable(TIMEOUT);

            if (reachable) {
                descripcion = "Prueba de ping";
                response.put("message", "✅ La IP " + ip + " está disponible (ping exitoso).");
                exitoso = true;
            } else {
                descripcion = "Prueba de ping fallida";
                response.put("message", "❌ La IP " + ip + " no respondió al ping.");
            }

        } catch (Exception e) {
            descripcion = "Error al hacer ping: " + e.getMessage();
            response.put("message", "⚠️ Error al hacer ping a la IP: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

        Evento evento = new Evento(ip, username, new Date(), descripcion, exitoso);
        eventoService.guardar(evento);

        return ResponseEntity.ok(response);
    }
}
