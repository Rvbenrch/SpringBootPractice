package com.novaBankpractice.controller;

import com.novaBankpractice.model.Cliente;
import com.novaBankpractice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes") // Todas las rutas de esta clase empezarán por aquí
public class ClienteController {

    @Autowired
    private ClienteRepository clienteRepository; // Inyección de dependencias

    // 1. GET: Listar todos los clientes (Prueba esto primero en Postman)
    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    // 2. POST: Crear un nuevo cliente desde Postman
    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {

        return clienteRepository.save(cliente);
    }
}