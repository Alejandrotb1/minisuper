package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 150, nullable = false, unique = true)
    private String codigo;

    @Column(length = 150, nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioCompra;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precioVenta;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(length = 255)
    private String imagen;

    @Column(length = 500)
    private String descripcion;

    @Column(nullable = false)
    private Integer stock=0;


    @Transient
    private int cantidad;



}
