package com.novaBankpractice.service.strategy;

import com.novaBankpractice.model.Cuenta;
import org.springframework.stereotype.Component;

@Component
public class RetiradaStrategy implements OperacionStrategy {
    @Override
    public void ejecutar(Cuenta cuenta, double cantidad) {
        if (cuenta.getSaldo() < cantidad) {
            throw new RuntimeException("Saldo insuficiente.");
        }
        cuenta.setSaldo(cuenta.getSaldo() - cantidad);
    }
}