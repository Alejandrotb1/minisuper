package com.inventario.inventario.controller.dto;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class DetalleVentasDTO {
    private Integer productoId;  // Clave que se espera en el JSON
    private Integer cantidad;
    private BigDecimal precio_unitario;  // En el JSON se espera esta clave
    private BigDecimal subtotal;
}

