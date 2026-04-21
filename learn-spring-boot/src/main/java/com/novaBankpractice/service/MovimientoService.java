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

    @org.springframework.transaction.annotation.Transactional
    public void realizarTransferencia(Long idOrigen, Long idDestino, Double cantidad) {
        Cuenta origen = cuentaRepository.findById(idOrigen)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no existe"));
        Cuenta destino = cuentaRepository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no existe"));

        // Aplicar estrategias
        strategyFactory.getStrategy(TipoMovimiento.TRANSFERENCIA_SALIENTE).ejecutar(origen, cantidad);
        strategyFactory.getStrategy(TipoMovimiento.TRANSFERENCIA_ENTRANTE).ejecutar(destino, cantidad);

        // Crear apuntes en el historial
        registrarMovimientoSimple(origen, cantidad, TipoMovimiento.TRANSFERENCIA_SALIENTE);
        registrarMovimientoSimple(destino, cantidad, TipoMovimiento.TRANSFERENCIA_ENTRANTE);

        cuentaRepository.save(origen);
        cuentaRepository.save(destino);
    }


    private void registrarMovimientoSimple(Cuenta cuenta, Double cantidad, TipoMovimiento tipo) {
        Movimiento mov = new Movimiento();
        mov.setTipo(tipo);
        mov.setCantidad(cantidad);
        mov.setCuenta(cuenta);
        movimientoRepository.save(mov);
    }
}