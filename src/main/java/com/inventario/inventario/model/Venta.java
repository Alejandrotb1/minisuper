package com.inventario.inventario.model;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.List;

import lombok.Data;


@Data
@Entity
@Table(name = "ventas")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ingreso_id", nullable = false)
    private Ingreso ingreso; // Asociada a un ingreso

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;



    @Column(nullable = false)
    private LocalTime hora;

    @PrePersist
    private void asignarHora() {
        this.hora = LocalTime.now();
    }

}
