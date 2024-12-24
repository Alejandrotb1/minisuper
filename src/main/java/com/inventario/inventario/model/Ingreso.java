package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "ingresos")
public class Ingreso extends Transaccion {

    @OneToOne(mappedBy = "ingreso", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Venta venta; // Relación opcional con Venta
}
