package com.novaBankpractice.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas") // Conecta con la tabla de tu schema.sql
public class Cuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_cuenta", nullable = false, unique = true, length = 34)
    private String numeroCuenta; // IBAN

    @Column(nullable = false)
    private Double saldo = 0.0; // Tal y como definiste, saldo inicial 0

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    // --- LA RELACIÓN CON EL CLIENTE ---
    @ManyToOne(optional = false) // "Muchas cuentas pueden pertenecer a un Cliente"
    @JoinColumn(name = "cliente_id", nullable = false) // El nombre de la columna en PostgreSQL
    private Cliente cliente;

    // Constructor vacío
    public Cuenta() {
    }

    // Callback para la fecha y el saldo por defecto antes de guardar
    @PrePersist
    protected void onCreate() {
        if (this.fechaCreacion == null) {
            this.fechaCreacion = LocalDateTime.now();
        }
        if (this.saldo == null) {
            this.saldo = 0.0;
        }
    }

    // --- GETTERS Y SETTERS ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumeroCuenta() { return numeroCuenta; }
    public void setNumeroCuenta(String numeroCuenta) { this.numeroCuenta = numeroCuenta; }
    public Double getSaldo() { return saldo; }
    public void setSaldo(Double saldo) { this.saldo = saldo; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }
}