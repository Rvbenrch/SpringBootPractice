package com.novaBankpractice.repository;

import com.novaBankpractice.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    // ¡Vacío! Spring Boot ya sabe cómo hacer INSERT, SELECT, UPDATE y DELETE
    // solo por heredar de JpaRepository<Entidad, Tipo_de_ID>.
}