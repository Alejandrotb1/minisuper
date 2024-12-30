package com.inventario.inventario.controller.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class IngresoVentaDTO {
    private String metodoPago;  // Método de pago de la venta
    private BigDecimal monto;   // Monto total de la venta
}
