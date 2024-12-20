package com.inventario.inventario.service;

import com.inventario.inventario.controller.dto.UsuarioRegistroDTO;
import com.inventario.inventario.model.Usuario;

import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UsuarioService extends UserDetailsService{

	public Usuario guardar(UsuarioRegistroDTO registroDTO);



//	public List<Usuario> listarUsuarios();
//
//
//
//
//	Usuario actualizarRoles(String email);
//	Usuario findByEmail(String email);
}
