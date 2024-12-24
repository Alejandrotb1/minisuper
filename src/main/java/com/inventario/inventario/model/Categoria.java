package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 75, nullable = false, unique = true)
    private String nombre;

    private String Descripcion;


}
