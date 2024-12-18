package com.inventario.inventario.categoria;

import jakarta.persistence.*;

@Entity
/*@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor*/
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 75, nullable = false, unique = true)
    private String nombre;

    public Categoria() {
    }

    public Categoria(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
