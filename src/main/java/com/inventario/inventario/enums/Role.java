package com.inventario.inventario.enums;

public enum Role {
    ROLE_ADMIN("Administrador con acceso completo"),
    ROLE_USER("Usuario con acceso limitado"),
    ROLE_CAJERO("Usuario con acceso a caja");

    private final String descripcion;

    Role(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
