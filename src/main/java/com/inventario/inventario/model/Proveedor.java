package com.inventario.inventario.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;


@Entity
public class Proveedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false, unique = true)
    @NotEmpty(message = "El nombre es obligatorio")
    @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres")
    private String nombre;
    @Column(length = 15)
    @Pattern(regexp = "^\\+?[0-9]*$", message = "Número de teléfono no válido")
    private String telefono;

    @Column(length = 150)
    private String direccion;

    @Column(length = 100, unique = true)
    @Email(message = "Formato de email no válido")
    private String email;

    @PrePersist
    @PreUpdate
    public void normalizeEmail() {
        if (email != null) {
            email = email.toLowerCase().trim();
        }
    }

    public Proveedor() {
    }

    public Proveedor(String nombre) {
        this.nombre = nombre;
    }

    public Proveedor(String nombre, String telefono, String direccion, String email) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
        this.email = email;
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres") String getNombre() {
        return nombre;
    }

    public void setNombre(@Size(min = 3, max = 100, message = "El nombre debe tener entre 3 y 100 caracteres") String nombre) {
        this.nombre = nombre;
    }

    public @Pattern(regexp = "^\\+?[0-9]*$", message = "Número de teléfono no válido") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@Pattern(regexp = "^\\+?[0-9]*$", message = "Número de teléfono no válido") String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public @Email(message = "Formato de email no válido") String getEmail() {
        return email;
    }

    public void setEmail(@Email(message = "Formato de email no válido") String email) {
        this.email = email;
    }
}
