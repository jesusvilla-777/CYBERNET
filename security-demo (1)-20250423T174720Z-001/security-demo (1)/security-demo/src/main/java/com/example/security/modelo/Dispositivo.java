package com.example.security.modelo;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "dispositivos")
public class Dispositivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false, unique = true)
    private String ip;

    @Column(nullable = false, unique = true)
    private String mac;

    @Column(nullable = false)
    private String estado; // "Activo" o "Inactivo"

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ultima_conexion")
    private Date ultimaConexion;

    // Constructor vacío
    public Dispositivo() {
    }

    public Dispositivo(String nombre, String ip, String mac, String estado, Date ultimaConexion) {
        this.nombre = nombre;
        this.ip = ip;
        this.mac = mac;
        this.estado = estado;
        this.ultimaConexion = ultimaConexion;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getUltimaConexion() {
        return ultimaConexion;
    }

    public void setUltimaConexion(Date ultimaConexion) {
        this.ultimaConexion = ultimaConexion;
    }
}
