package com.inventario.inventario.repository;

import com.inventario.inventario.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {


//    @Query("SELECT v FROM Venta v LEFT JOIN FETCH v.ingreso WHERE v.id = :id")
//    Venta findByIdWithIngreso(@Param("id") Long id);

    @Query("SELECT v FROM Venta v LEFT JOIN FETCH v.ingreso LEFT JOIN FETCH v.cliente")
    List<Venta> obtenerTodasLasVentasConRelaciones();



}
