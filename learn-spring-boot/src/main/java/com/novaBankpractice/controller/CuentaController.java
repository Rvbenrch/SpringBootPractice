package com.novaBankpractice.controller;

import com.novaBankpractice.model.Cuenta;
import com.novaBankpractice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.novaBankpractice.repository.*;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @Autowired
    private CuentaRepository cuentaRepository;
    @GetMapping
    public List<Cuenta> listarCuentas() {
        return cuentaService.listarCuentas();
    }
    @GetMapping("/numero/{numero}")
    public Cuenta buscarPorNumero(@PathVariable String numero) {
        return cuentaRepository.findByNumeroCuenta(numero)
                .orElseThrow(() -> new RuntimeException("Cuenta " + numero + " no encontrada."));
    }

    @PostMapping("/cliente/{clienteId}")
    public Cuenta crearCuenta(@PathVariable Long clienteId) {
        return cuentaService.crearCuenta(clienteId);
    }
}