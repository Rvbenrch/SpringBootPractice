package com.novaBankpractice.controller;

import com.novaBankpractice.model.Movimiento;
import com.novaBankpractice.service.MovimientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
public class MovimientoController {

    @Autowired
    private MovimientoService movimientoService;

    // Equivalente a tu opción "Historial de movimientos"
    @GetMapping("/cuenta/{cuentaId}")
    public List<Movimiento> listarMovimientosCuenta(@PathVariable Long cuentaId) {
        return movimientoService.listarMovimientosPorCuenta(cuentaId);
    }

    // Equivalente a tus opciones de ingresar/retirar/transferir
    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(movimiento);
    }
}