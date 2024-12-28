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
    private Long id;


    private String nombre;
    private String telefono;
    @Column(unique = true, length = 11)
    private String ci_nit;


    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Venta> ventas;

    @PrePersist
    public void setNombreDefault() {
        if (this.nombre == null) {
            this.nombre = "No Registrado";  // Nombre predeterminado para clientes sin datos
        }
    }


}

