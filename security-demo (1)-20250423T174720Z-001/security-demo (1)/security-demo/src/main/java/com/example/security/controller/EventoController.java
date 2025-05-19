package com.example.security.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.security.dto.EventoDTO;
import com.example.security.modelo.Evento;
import com.example.security.servicios.EventoService;

@RestController
@RequestMapping("/api/events")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    // Obtener todos los eventos
    @GetMapping
    public ResponseEntity<List<Evento>> getAllEvents() {
        List<Evento> eventos = eventoService.findAll();
        if (eventos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(eventos); // Retorna 204 si no hay eventos
        }
        return ResponseEntity.ok(eventos);
    }

    // Registrar un nuevo evento
    @PostMapping("/registrar")
    public ResponseEntity<Evento> registrarEvento(@RequestBody @Validated EventoDTO dto) {
        try {
            Evento nuevo = eventoService.registrarEvento(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
        } catch (Exception e) {
            // Manejo de excepciones, puedes agregar más detalles en el mensaje
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
