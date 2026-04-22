package com.novaBankpractice.factory;
import com.novaBankpractice.model.*;

public class MovimientoFactory {

    public static Movimiento crearIngreso(Cuenta cuenta, double cantidad) {
        return Movimiento.builder()
                .cuenta(cuenta)
                .tipo(TipoMovimiento.DEPOSITO)
                .cantidad(cantidad)
                .build();
    }

    public static Movimiento crearRetiro(Cuenta cuenta, double cantidad) {
        return Movimiento.builder()
                .cuenta(cuenta)
                .tipo(TipoMovimiento.RETIRO)
                .cantidad(cantidad)
                .build();
    }

    public static Movimiento crearTransferenciaSaliente(Cuenta cuentaOrigen, double cantidad) {
        return Movimiento.builder()
                .cuenta(cuentaOrigen)
                .tipo(TipoMovimiento.TRANSFERENCIA_SALIENTE)
                .cantidad(cantidad)
                .build();
    }

    public static Movimiento crearTransferenciaEntrante(Cuenta cuentaDestino, double cantidad) {
        return Movimiento.builder()
                .cuenta(cuentaDestino)
                .tipo(TipoMovimiento.TRANSFERENCIA_ENTRANTE)
                .cantidad(cantidad)
                .build();
    }
}