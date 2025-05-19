package com.example.security.servicios;

import com.example.security.modelo.Dispositivo;
import com.example.security.repositorio.DispositivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DispositivoService {

    @Autowired
    private DispositivoRepository dispositivoRepository;

    /**
     * Devuelve todos los dispositivos
     */
    public List<Dispositivo> findAll() {
        return dispositivoRepository.findAll();
    }
}
