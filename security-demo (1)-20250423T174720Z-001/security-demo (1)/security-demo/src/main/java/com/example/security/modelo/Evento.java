package com.example.security.modelo;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Entity
@Table(name = "eventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String ip;

    @Column(nullable = true)
    private String mac;

    @Column(nullable = false)
    private LocalDateTime fecha;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private boolean activo;

    public Evento() {
        // Constructor sin argumentos necesario para JPA
    }

    // Constructor principal con LocalDateTime
    public Evento(String ip, String mac, LocalDateTime fecha, String descripcion, boolean activo) {
        this.ip = ip;
        this.mac = mac;
        this.fecha = fecha != null ? fecha : LocalDateTime.now();
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // Constructor útil para usar con java.util.Date y sin mac
    public Evento(String ip, String mac, Date fechaDate, String descripcion, boolean activo) {
        this.ip = ip;
        this.mac = mac;
        if (fechaDate != null) {
            this.fecha = LocalDateTime.ofInstant(fechaDate.toInstant(), ZoneId.systemDefault());
        } else {
            this.fecha = LocalDateTime.now();
        }
        this.descripcion = descripcion;
        this.activo = activo;
    }

    // Constructor sin mac, para usar en tu controlador (puedes pasar null si no tienes mac)
    public Evento(String ip, String descripcion, Date fechaDate, boolean activo) {
        this(ip, null, fechaDate, descripcion, activo);
    }

    // Getters y Setters

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMac() {
        return mac;
    }
    public void setMac(String mac) {
        this.mac = mac;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }
    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    // Setter para Date si alguna vez lo necesitas
    public void setFecha(Date fechaDate) {
        if (fechaDate != null) {
            this.fecha = LocalDateTime.ofInstant(fechaDate.toInstant(), ZoneId.systemDefault());
        }
    }

    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }
    public void setActivo(boolean activo) {
        this.activo = activo;
    }

}
