package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@DiscriminatorValue("INGRESO")  // Valor discriminador para Ingreso
public class Ingreso extends Transaccion {

    @OneToOne(mappedBy = "ingreso", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Venta venta;  // Relación específica para Ingreso
}
