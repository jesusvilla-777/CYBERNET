package com.example.security.controller;

import com.example.security.modelo.Dispositivo;
import com.example.security.servicios.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dispositivos")
public class DispositivoController {

    @Autowired
    private DispositivoService dispositivoService;

    /**
     * Obtiene todos los dispositivos
     */
    @GetMapping
    public ResponseEntity<List<Dispositivo>> getAllDispositivos() {
        List<Dispositivo> dispositivos = dispositivoService.findAll();
        return new ResponseEntity<>(dispositivos, HttpStatus.OK);
    }
}
