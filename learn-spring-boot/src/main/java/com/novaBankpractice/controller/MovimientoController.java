package com.novaBankpractice.controller;

import com.novaBankpractice.dto.TransferenciaDTO;
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


    @GetMapping("/cuenta/{cuentaId}")
    public List<Movimiento> listarMovimientosCuenta(@PathVariable Long cuentaId) {
        return movimientoService.listarMovimientosPorCuenta(cuentaId);
    }


    @PostMapping
    public Movimiento registrarMovimiento(@RequestBody Movimiento movimiento) {
        return movimientoService.registrarMovimiento(movimiento);
    }
    @PostMapping("/transferencia")
    public String realizarTransferencia(@RequestBody TransferenciaDTO datos) {
        movimientoService.realizarTransferencia(
                datos.getIdOrigen(),
                datos.getIdDestino(),
                datos.getCantidad()
        );
        return "Transferencia de " + datos.getCantidad() + "€ realizada con éxito.";
    }
}