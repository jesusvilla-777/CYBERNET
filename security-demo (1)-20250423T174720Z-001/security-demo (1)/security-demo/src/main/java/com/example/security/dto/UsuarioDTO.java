package com.example.security.dto;

import java.util.List;

public class UsuarioDTO {

    private String username;
    private String password;
    private String confirmPassword; // Para validar que coincida la contraseña
    private String nombre;
    private String apellido;
    private String email;
    private List<String> roles;
    private boolean activo;

    public UsuarioDTO() {}

    public UsuarioDTO(String username, String password, String confirmPassword,
                      String nombre, String apellido, String email,
                      List<String> roles, boolean activo) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.roles = roles;
        this.activo = activo;
    }

    // Getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "username='" + (username != null ? username : "N/A") + '\'' +
                ", nombre='" + (nombre != null ? nombre : "N/A") + '\'' +
                ", apellido='" + (apellido != null ? apellido : "N/A") + '\'' +
                ", email='" + (email != null ? email : "N/A") + '\'' +
                ", roles=" + (roles != null ? roles.toString() : "N/A") +
                ", activo=" + activo +
                '}';
    }
}
