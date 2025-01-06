package com.inventario.inventario.service;

import com.inventario.inventario.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class CompraService {

    @Autowired
    GastoRepository gastoRepository;












    public BigDecimal obtenerTotalComprasDelMes(LocalDate inicio, LocalDate fin){
        return gastoRepository.obtenerMontoTotalComprasDelMes(inicio, fin).orElse(BigDecimal.ZERO);
    }
}
