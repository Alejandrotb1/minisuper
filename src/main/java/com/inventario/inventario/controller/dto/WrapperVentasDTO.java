package com.inventario.inventario.controller.dto;

import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Venta;

import java.util.List;

public class WrapperVentasDTO {

    private Venta venta; // Objeto que contiene la información de la venta
    private Ingreso ingreso; // Objeto que contiene la información del ingreso
    private List<ProductoDTO> productos; // Lista de productos de la venta

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

    public List<ProductoDTO> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDTO> productos) {
        this.productos = productos;
    }
}
