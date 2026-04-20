package com.novaBankpractice.repository;

import com.novaBankpractice.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
    // Más adelante añadiremos aquí un método para buscar cuentas por IBAN
}