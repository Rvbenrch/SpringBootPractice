package com.novaBankpractice.service;

import com.novaBankpractice.model.Cliente;
import com.novaBankpractice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente con ID " + id + " no encontrado."));
    }

    public Cliente buscarPorDni(String dni) {
        return clienteRepository.findByDni(dni)
                .orElseThrow(() -> new RuntimeException("Cliente con DNI " + dni + " no encontrado."));
    }
}
