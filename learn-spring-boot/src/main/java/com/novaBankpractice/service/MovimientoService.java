package com.novaBankpractice.service;

import com.novaBankpractice.model.Cuenta;
import com.novaBankpractice.model.Movimiento;
import com.novaBankpractice.model.TipoMovimiento;
import com.novaBankpractice.repository.CuentaRepository;
import com.novaBankpractice.repository.MovimientoRepository;
import com.novaBankpractice.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovimientoService {

    @Autowired
    private MovimientoRepository movimientoRepository;

    @Autowired
    private CuentaRepository cuentaRepository;
    @Autowired
    private OperacionStrategyFactory strategyFactory;


    public List<Movimiento> listarMovimientosPorCuenta(Long cuentaId) {
        return movimientoRepository.findByCuentaId(cuentaId);
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Long idCuenta = movimiento.getCuenta().getId();

        Cuenta cuentaReal = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Error: La cuenta con ID " + idCuenta + " no existe."));

        OperacionStrategy strategy = strategyFactory.getStrategy(movimiento.getTipo());
        OperacionContext context = new OperacionContext();
        context.setStrategy(strategy);
        context.ejecutar(cuentaReal,movimiento.getCantidad());


        context.ejecutar(cuentaReal, movimiento.getCantidad());


        movimiento.setCuenta(cuentaReal);
        cuentaRepository.save(cuentaReal);

        return movimientoRepository.save(movimiento);
    }
}