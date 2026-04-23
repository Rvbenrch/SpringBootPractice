package com.novaBankpractice.service;

import com.novaBankpractice.model.*;
import com.novaBankpractice.repository.CuentaRepository;
import com.novaBankpractice.repository.MovimientoRepository;
import com.novaBankpractice.service.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<Movimiento> listarMovimientosPorRangoFechas(Long cuentaId, LocalDateTime inicio, LocalDateTime fin) {
        return movimientoRepository.findByCuentaIdAndFechaBetween(cuentaId, inicio, fin);
    }

    public Movimiento registrarMovimiento(Movimiento movimiento) {
        Long idCuenta = movimiento.getCuenta().getId();

        Cuenta cuentaReal = cuentaRepository.findById(idCuenta)
                .orElseThrow(() -> new RuntimeException("Error: La cuenta con ID " + idCuenta + " no existe."));

        // Ejecutar la estrategia según el tipo de movimiento
        OperacionStrategy strategy = strategyFactory.getStrategy(movimiento.getTipo());
        strategy.ejecutar(cuentaReal, movimiento.getCantidad());

        // Guardar la cuenta con el nuevo saldo
        cuentaRepository.save(cuentaReal);

        // Guardar el movimiento con la fecha actual si no la tiene
        if (movimiento.getFecha() == null) {
            movimiento.setFecha(LocalDateTime.now());
        }
        movimiento.setCuenta(cuentaReal);
        return movimientoRepository.save(movimiento);
    }

    @org.springframework.transaction.annotation.Transactional
    public void realizarTransferencia(Long idOrigen, Long idDestino, Double cantidad) {
        Cuenta origen = cuentaRepository.findById(idOrigen)
                .orElseThrow(() -> new RuntimeException("Cuenta origen no existe"));
        Cuenta destino = cuentaRepository.findById(idDestino)
                .orElseThrow(() -> new RuntimeException("Cuenta destino no existe"));

        // 1. Registrar el movimiento de salida
        Movimiento movSalida = new Movimiento();
        movSalida.setCuenta(origen);
        movSalida.setCantidad(cantidad);
        movSalida.setTipo(TipoMovimiento.TRANSFERENCIA_SALIENTE);
        registrarMovimiento(movSalida);

        // 2. Registrar el movimiento de entrada
        Movimiento movEntrada = new Movimiento();
        movEntrada.setCuenta(destino);
        movEntrada.setCantidad(cantidad);
        movEntrada.setTipo(TipoMovimiento.TRANSFERENCIA_ENTRANTE);
        registrarMovimiento(movEntrada);
    }


}