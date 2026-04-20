package com.novaBankpractice.controller;

import com.novaBankpractice.model.Cliente;
import com.novaBankpractice.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService; // ¡Cambio clave! Ahora usamos el Service

    @GetMapping
    public List<Cliente> listarClientes() {
        return clienteService.listarTodos(); // Llamamos al Service
    }

    @PostMapping
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteService.crearCliente(cliente); // Llamamos al Service
    }
}