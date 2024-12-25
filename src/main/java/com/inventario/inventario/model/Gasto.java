package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@DiscriminatorValue("GASTO")  // Valor específico para la subclase
public class Gasto extends Transaccion {

    @OneToOne(mappedBy = "gasto", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Compra compra;  // Relación con Compra
}

