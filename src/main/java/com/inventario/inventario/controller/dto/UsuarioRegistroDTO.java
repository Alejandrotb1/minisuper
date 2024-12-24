package com.inventario.inventario.controller.dto;

import com.inventario.inventario.enums.Role;

import java.util.List;

public class UsuarioRegistroDTO {

	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;

	private List<Role> roles; // Para capturar los roles seleccionados desde el formulario

	public UsuarioRegistroDTO() {
	}

	public UsuarioRegistroDTO(Integer id, String nombre, String apellido, String email, String password, List<Role> roles) {
		this.id = id;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
		this.roles = roles;
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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	//	private boolean isAdmin;
//
//	public boolean isAdmin() {
//		return isAdmin;
//	}
//
//	public void setAdmin(boolean admin) {
//		isAdmin = admin;
//	}



}
