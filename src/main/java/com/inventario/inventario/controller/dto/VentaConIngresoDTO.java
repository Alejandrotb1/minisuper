package com.inventario.inventario.controller.dto;

import lombok.Data;

@Data
public class VentaConIngresoDTO {
    private IngresoVentaDTO ingreso;
    private VentaDTO venta;
}