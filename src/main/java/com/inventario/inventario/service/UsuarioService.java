package com.inventario.inventario.service;

import com.inventario.inventario.controller.dto.UsuarioRegistroDTO;
import com.inventario.inventario.model.Usuario;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;


public interface UsuarioService extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);



	// Método para obtener todos los usuarios
	List<Usuario> obtenerTodosLosUsuarios();

	// Método para cargar un usuario por su nombre de usuario (usado para autenticación)
	@Override
	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
