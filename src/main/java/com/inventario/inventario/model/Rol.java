package com.inventario.inventario.model;


import com.inventario.inventario.enums.Role;
import jakarta.persistence.*;
@Entity
@Table(name = "rol")
public class Rol {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true)
	private Role nombre;

	@Column(nullable = false)
	private String descripcion;

	// Constructor adecuado para crear el rol con enum
	public Rol(Role nombre) {
		this.nombre = nombre;
		this.descripcion = nombre.getDescripcion(); // Obtener descripción del enum
	}

	// Getters y setters
	public Role getNombre() {
		return nombre;
	}

	public void setNombre(Role nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
}
