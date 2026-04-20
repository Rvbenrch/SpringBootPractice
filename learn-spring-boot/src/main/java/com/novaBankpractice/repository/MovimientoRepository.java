package com.novaBankpractice.repository;

import com.novaBankpractice.model.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {

    // Spring Boot crea la consulta SQL automáticamente solo leyendo este nombre
    List<Movimiento> findByCuentaId(Long cuentaId);
}