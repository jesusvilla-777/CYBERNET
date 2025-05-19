package com.example.security.servicios;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.security.dto.EventoDTO;
import com.example.security.modelo.Evento;
import com.example.security.repositorio.EventoRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private HttpServletRequest request;

    public List<Evento> findAll() {
        return eventoRepository.findAll();
    }
    public Evento registrarEvento(EventoDTO eventoDTO) {
        Evento evento = new Evento();

        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            ip = request.getRemoteAddr();
        }

        evento.setIp(ip);
        evento.setDescripcion(eventoDTO.getDescripcion());
        evento.setActivo(true);
        evento.setMac("00:00:00:00:00:00"); // Placeholder
        evento.setFecha(LocalDateTime.now()); // ✅ Aquí se agrega la hora actual

        return eventoRepository.save(evento);
    }
    public void guardar(Evento evento) {
        eventoRepository.save(evento);
    }

}
