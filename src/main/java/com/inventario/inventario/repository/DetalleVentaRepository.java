package com.inventario.inventario.repository;

import com.inventario.inventario.model.DetalleVenta;
import com.inventario.inventario.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleVentaRepository extends JpaRepository<DetalleVenta, Long> {
}
