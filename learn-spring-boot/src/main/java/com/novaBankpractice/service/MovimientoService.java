package com.novaBankpractice.service;

import com.novaBankpractice.model.Cuenta;
import com.novaBankpractice.model.Movimiento;
import com.novaBankpractice.model.TipoMovimiento;
import com.novaBankpractice.repository.CuentaRepository;
import com.novaBankpractice.repository.MovimientoRepository;
import com.novaBankpractice.service.strategy.IngresoStrategy;
import com.novaBankpractice.service.strategy.OperacionContext;
import com.novaBankpractice.service.strategy.RetiradaStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;

    // Cumpliendo con el "listado por cuenta" de tu Caso Práctico 2
    public List<Movimiento> listarMovimientosPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Long idCuenta = movimiento.getCuenta().getId();

        Cuenta cuentaReal = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Error: La cuenta con ID " + idCuenta + " no existe."));

        // --- USO DEL PATRÓN STRATEGY ---
        OperacionContext context = new OperacionContext();

        if (movimiento.getTipo() == com.novaBankpractice.model.TipoMovimiento.DEPOSITO) {
            context.setStrategy(new IngresoStrategy());
        } else if (movimiento.getTipo() == com.novaBankpractice.model.TipoMovimiento.RETIRO) {
            context.setStrategy(new RetiradaStrategy());
        } else {
            throw new RuntimeException("Operación no soportada todavía.");
        }

        // El contexto ejecuta la estrategia elegida y actualiza el saldo de cuentaReal
        context.ejecutar(cuentaReal, movimiento.getCantidad());
        // --------------------------------

        movimiento.setCuenta(cuentaReal);
        cuentaRepository.save(cuentaReal);

        return movimientoRepository.save(movimiento);
    }
}