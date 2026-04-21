package com.novaBankpractice.service;

import com.novaBankpractice.repository.ClienteRepository;
import com.novaBankpractice.repository.CuentaRepository;
import com.novaBankpractice.repository.MovimientoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DataResetService {

    private final ClienteRepository clienteRepository;
    private final CuentaRepository cuentaRepository;
    private final MovimientoRepository movimientoRepository;

    public DataResetService(ClienteRepository clienteRepository, CuentaRepository cuentaRepository, MovimientoRepository movimientoRepository) {
        this.clienteRepository = clienteRepository;
        this.cuentaRepository = cuentaRepository;
        this.movimientoRepository = movimientoRepository;
    }

    @Transactional
    public void resetAll() {
        movimientoRepository.deleteAll();
        cuentaRepository.deleteAll();
        clienteRepository.deleteAll();
    }
}
