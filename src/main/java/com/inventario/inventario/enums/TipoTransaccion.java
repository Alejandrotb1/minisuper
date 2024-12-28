package com.inventario.inventario.enums;


public enum TipoTransaccion {
    INGRESO("Transacción de ingreso"),
    GASTO("Transacción de gasto"),
    VENTAS("Transacción de ventas"),
    COMPRA_INVENTARIO("Transacción de compra de inventario");

    private final String descripcion;

    TipoTransaccion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
