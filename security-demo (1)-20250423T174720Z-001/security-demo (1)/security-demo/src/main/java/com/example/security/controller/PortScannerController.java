package com.example.security.controller;

import com.example.security.modelo.Evento;
import com.example.security.servicios.EventoService;
import com.example.security.servicios.PortScannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.concurrent.*;

@RestController
@RequestMapping("/api")
public class PortScannerController {

    @Autowired
    private PortScannerService scannerService;

    @Autowired
    private EventoService eventoService;

    @PostMapping("/scan")
    public Map<String, Object> scanPorts(@RequestBody Map<String, String> request) throws ExecutionException, InterruptedException {
        String ip = request.get("ip");
        CompletableFuture<List<Integer>> result = scannerService.scanPorts(ip);
        List<Integer> openPorts = result.get();

        // Obtener usuario autenticado
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        // Guardar evento
        String descripcion = "Escaneo de puertos - Puertos abiertos: " + openPorts.size();
        Evento evento = new Evento(ip, username, new Date(), descripcion, true);
        eventoService.guardar(evento);

        return Map.of("ports", openPorts);
    }
}
