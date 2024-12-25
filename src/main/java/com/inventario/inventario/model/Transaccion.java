package com.inventario.inventario.model;

import com.inventario.inventario.enums.TipoTransaccion;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)  // Usar SINGLE_TABLE para la herencia
@DiscriminatorColumn(name = "tipo_transaccion", discriminatorType = DiscriminatorType.STRING) // Columna discriminadora
@Table(name = "transacciones") // Tabla única para todos los tipos de transacciones
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private TipoTransaccion tipo; // INGRESO o GASTO

    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal monto;

    @Column(nullable = false)
    private LocalDate fecha;

    @Column(length = 80)
    private String concepto;
    @Column(length = 500)
    private String detalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(length = 20)
    private String metodoPago;

    @PrePersist
    private void asignarFecha() {
        this.fecha = LocalDate.now();
    }
}


