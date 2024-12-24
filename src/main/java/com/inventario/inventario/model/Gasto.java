package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "gastos")
public class Gasto extends Transaccion {

    @OneToOne(mappedBy = "gasto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Compra compra; // Relación opcional con Compra
}

