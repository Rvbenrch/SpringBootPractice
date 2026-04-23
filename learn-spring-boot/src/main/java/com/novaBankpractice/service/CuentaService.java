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
    public Cuenta buscarPorNumero(String numero) {
        return cuentaRepository.findByNumeroCuenta(numero)
                .orElseThrow(() -> new RuntimeException("Error: La cuenta con número " + numero + " no existe."));
    }

    public Cuenta crearCuenta(Long clienteId) {

        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Error: El cliente con ID " + clienteId + " no existe."));

        Cuenta cuenta = new Cuenta();
        cuenta.setCliente(cliente);
        cuenta.setNumeroCuenta(generarNumeroCuentaUnico());
        cuenta.setSaldo(0.0);

        return cuentaRepository.save(cuenta);
    }

    private String generarNumeroCuentaUnico() {
        String nuevoNumero;
        boolean existe;
        do {
            // Generamos un número ficticio, por ejemplo: ES + 20 dígitos aleatorios
            StringBuilder sb = new StringBuilder("ES");
            for (int i = 0; i < 20; i++) {
                sb.append((int) (Math.random() * 10));
            }
            nuevoNumero = sb.toString();
            // Verificamos que no exista ya en la base de datos
            existe = cuentaRepository.findByNumeroCuenta(nuevoNumero).isPresent();
        } while (existe);
        
        return nuevoNumero;
    }
}