package com.novaBankpractice.service;

import com.novaBankpractice.model.Cliente;
import com.novaBankpractice.model.Cuenta;
import com.novaBankpractice.repository.ClienteRepository;
import com.novaBankpractice.repository.CuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaService {

    @Autowired
    private CuentaRepository cuentaRepository;

    // Inyectamos también el repositorio de clientes para poder buscar
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cuenta> listarCuentas() {
        return cuentaRepository.findAll();
    }

    public Cuenta crearCuenta(Long clienteId, String numeroCuenta) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Error: El cliente con ID " + clienteId + " no existe."));


        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta(numeroCuenta);
        cuenta.setSaldo(0.0);

        return cuentaRepository.save(cuenta);
    }
}