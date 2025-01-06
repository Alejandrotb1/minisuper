package com.inventario.inventario.repository;

import com.inventario.inventario.model.Ingreso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

public interface IngresoRepository extends JpaRepository<Ingreso, Long> {




    @Query("SELECT SUM(i.monto) FROM Ingreso i WHERE i.fecha BETWEEN :inicio AND :fin")
    Optional<BigDecimal> sumarIngresosEntreFechas(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);

    @Query("SELECT SUM(i.monto) FROM Ingreso i WHERE i.fecha BETWEEN :inicio AND :fin AND i.venta IS NOT NULL")
    Optional<BigDecimal> obtenerMontoTotalVentasDelMes(@Param("inicio") LocalDate inicio, @Param("fin") LocalDate fin);




}
