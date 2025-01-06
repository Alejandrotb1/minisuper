package com.inventario.inventario.repository;

import com.inventario.inventario.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Long> {


//    @Query("SELECT v FROM Venta v LEFT JOIN FETCH v.ingreso WHERE v.id = :id")
//    Venta findByIdWithIngreso(@Param("id") Long id);

    @Query("SELECT v FROM Venta v LEFT JOIN FETCH v.ingreso LEFT JOIN FETCH v.cliente")
    List<Venta> obtenerTodasLasVentasConRelaciones();


    @Query("SELECT v FROM Venta v JOIN v.ingreso i ORDER BY i.fecha DESC")
    List<Venta> findAllOrderByFechaIngresoDesc();


        @Query("SELECT v FROM Venta v JOIN v.ingreso i ORDER BY i.fecha DESC, v.hora DESC")
        List<Venta> findAllOrderByFechaAndHoraDesc();



//contar ventas del dia
    @Query("SELECT COUNT(v) FROM Venta v WHERE v.ingreso.fecha = :fecha")
    long countByFecha(@Param("fecha") LocalDate fecha);
    @Query("SELECT SUM(v.ingreso.monto) FROM Venta v WHERE v.ingreso.fecha = :fecha")
    BigDecimal sumarMontoVentasDelDia(@Param("fecha") LocalDate fecha);


}
