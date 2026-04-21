package com.novaBankpractice.service.strategy;

import com.novaBankpractice.model.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class IngresoStrategy implements OperacionStrategy {
    @Override
    public void ejecutar(Cuenta cuenta, double cantidad) {
        cuenta.setSaldo(cuenta.getSaldo() + cantidad);
    }
}