package com.novaBankpractice.service.strategy;

import com.novaBankpractice.model.Cuenta;

public interface OperacionStrategy {
    void ejecutar(Cuenta cuenta, double cantidad);
}