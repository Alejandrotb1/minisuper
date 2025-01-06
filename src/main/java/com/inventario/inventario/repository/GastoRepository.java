package com.inventario.inventario.repository;

import com.inventario.inventario.model.Gasto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface GastoRepository extends JpaRepository<Gasto, Long> {

    @Query("SELECT SUM(c.monto) FROM Gasto c WHERE c.fecha BETWEEN :inicio AND :fin")
    Optional<BigDecimal> sumarGastosEntreFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    @Query("SELECT SUM(c.monto) FROM Gasto c WHERE c.fecha BETWEEN :inicio AND :fin AND c.compra IS NOT NULL")
    Optional<BigDecimal> obtenerMontoTotalComprasDelMes(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

}
