package com.inventario.inventario.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String nombre;
    private String telefono;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    @PrePersist
    public void setNombreDefault() {
        if (this.nombre == null) {
            this.nombre = "No Registrado";  // Nombre predeterminado para clientes sin datos
        }
    }


}

