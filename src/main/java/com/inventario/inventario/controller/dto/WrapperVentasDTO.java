package com.inventario.inventario.controller.dto;

import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Venta;

public class WrapperVentasDTO {
    private Venta venta;
    private Ingreso ingreso;

    // Getters y setters
    public Venta getVenta() {
        return venta;
    }

    public void setVenta(Venta venta) {
        this.venta = venta;
    }

    public Ingreso getIngreso() {
        return ingreso;
    }

    public void setIngreso(Ingreso ingreso) {
        this.ingreso = ingreso;
    }
}
