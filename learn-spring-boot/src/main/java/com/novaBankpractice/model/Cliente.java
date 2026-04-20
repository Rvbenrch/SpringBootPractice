package com.novaBankpractice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "clientes") // Conecta con la tabla de tu schema.sql
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // El SERIAL autogenerado
    private Long id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 150)
    private String apellidos;

    @Column(nullable = false, unique = true, length = 20)
    private String dni;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(nullable = false, unique = true, length = 20)
    private String telefono;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    // Constructor vacío obligatorio para que JPA pueda mapear los datos
    public Cliente() {
        this.fechaCreacion = LocalDateTime.now();
    }
    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
    }
    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }
    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // --- PATRÓN BUILDER (Mantenemos tu diseño del CP2) ---
    public static class Builder {
        private Long id;
        private String nombre;
        private String apellidos;
        private String dni;
        private String email;
        private String telefono;

        public Builder id(Long id) { this.id = id; return this; }
        public Builder nombre(String nombre) { this.nombre = nombre; return this; }
        public Builder apellidos(String apellidos) { this.apellidos = apellidos; return this; }
        public Builder dni(String dni) { this.dni = dni; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder telefono(String telefono) { this.telefono = telefono; return this; }

        public Cliente build() {
            Cliente cliente = new Cliente();
            cliente.setId(this.id);
            cliente.setNombre(this.nombre);
            cliente.setApellidos(this.apellidos);
            cliente.setDni(this.dni);
            cliente.setEmail(this.email);
            cliente.setTelefono(this.telefono);
            return cliente;
        }
    }
}