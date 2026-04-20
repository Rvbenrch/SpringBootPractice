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

    public Cuenta crearCuenta(Cuenta cuenta) {
        // 1. Extraemos el ID del cliente que nos llega desde Postman
        Long idCliente = cuenta.getCliente().getId();

        // 2. Buscamos al cliente REAL en la base de datos
        // (Si no existe, lanzamos un error que más adelante controlaremos)
        Cliente clienteReal = clienteRepository.findById(idCliente)
                .orElseThrow(() -> new RuntimeException("Error: El cliente con ID " + idCliente + " no existe."));

        // 3. Le asignamos este cliente real y completo a la cuenta
        cuenta.setCliente(clienteReal);

        // 4. Ahora sí, guardamos la cuenta de forma segura
        return cuentaRepository.save(cuenta);
    }
}