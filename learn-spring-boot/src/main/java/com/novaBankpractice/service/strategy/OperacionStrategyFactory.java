package com.novaBankpractice.service.strategy;

import com.novaBankpractice.model.TipoMovimiento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OperacionStrategyFactory {

    @Autowired
    private IngresoStrategy ingresoStrategy;

    @Autowired
    private RetiradaStrategy retiradaStrategy;

    public OperacionStrategy getStrategy(TipoMovimiento tipo) {
        return switch (tipo) {
            case DEPOSITO -> ingresoStrategy;
            case RETIRO -> retiradaStrategy;
            default -> throw new IllegalArgumentException("Tipo de movimiento no soportado: " + tipo);
        };
    }
}