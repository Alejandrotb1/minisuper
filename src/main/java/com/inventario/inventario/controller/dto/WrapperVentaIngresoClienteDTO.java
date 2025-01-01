package com.inventario.inventario.controller.dto;

import com.inventario.inventario.model.Cliente;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Venta;
import lombok.Data;

@Data
public class WrapperVentaIngresoClienteDTO {

    private Venta venta;
    private Ingreso ingreso;
    private Cliente cliente;

    public WrapperVentaIngresoClienteDTO(Venta venta, Ingreso ingreso, Cliente cliente) {
        this.venta = venta;
        this.ingreso = ingreso;
        this.cliente = cliente;
    }
}
