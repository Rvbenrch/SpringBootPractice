package com.novaBankpractice.controller;

import com.novaBankpractice.model.Cuenta;
import com.novaBankpractice.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cuentas")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping
    public List<Cuenta> listarCuentas() {
        return cuentaService.listarCuentas();
    }

    @GetMapping("/numero/{numero}")
    public Cuenta buscarPorNumero(@PathVariable String numero) {
        // Toda la lógica y manejo de errores se delega al Servicio
        return cuentaService.buscarPorNumero(numero);
    }

    @PostMapping("/cliente/{clienteId}")
    public Cuenta crearCuenta(@PathVariable Long clienteId) {
        return cuentaService.crearCuenta(clienteId);
    }
}