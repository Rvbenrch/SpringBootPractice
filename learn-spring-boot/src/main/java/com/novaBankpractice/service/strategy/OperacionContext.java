package com.novaBankpractice.service.strategy;

import com.novaBankpractice.model.Cuenta;
import lombok.Setter;

public class OperacionContext {

    @Setter
    private OperacionStrategy strategy;


    public void ejecutar(Cuenta cuenta, double cantidad) {
        if (this.strategy == null) {
            throw new IllegalStateException("Error: No se ha asignado ninguna estrategia de operación.");
        }
        this.strategy.ejecutar(cuenta, cantidad);
    }
}