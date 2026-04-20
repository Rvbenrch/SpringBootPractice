package com.novaBankpractice.service;

import com.novaBankpractice.model.Cliente;
import com.novaBankpractice.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Le dice a Spring que esta clase contiene tu lógica de negocio
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Cliente crearCliente(Cliente cliente) {

        return clienteRepository.save(cliente);
    }
}