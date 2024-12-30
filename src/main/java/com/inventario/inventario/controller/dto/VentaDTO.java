package com.inventario.inventario.controller.dto;

import lombok.Data;

import java.util.List;
@Data
public class VentaDTO {
    private Long clienteId;
    private List<DetalleVentasDTO> detalleVentas;
}