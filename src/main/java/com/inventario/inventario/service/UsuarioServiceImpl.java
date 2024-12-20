package com.inventario.inventario.service;

import com.inventario.inventario.controller.dto.UsuarioRegistroDTO;
import com.inventario.inventario.model.Rol;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.UsuarioRepository;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder passwordEncoder;

	// Constructor para inyección de dependencias
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
		this.usuarioRepository = usuarioRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Usuario guardar(UsuarioRegistroDTO registroDTO) {
		// Verificar si el usuario ya existe
		if (usuarioRepository.findByEmail(registroDTO.getEmail()) != null) {
			throw new IllegalArgumentException("El email ya está registrado.");
		}

		// Crear el nuevo usuario
		Usuario usuario = new Usuario(
				registroDTO.getNombre(),
				registroDTO.getApellido(),
				registroDTO.getEmail(),
				passwordEncoder.encode(registroDTO.getPassword()),
				Arrays.asList(new Rol("ROLE_USER"))
		);
		return usuarioRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(username);
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}
		return new User(
				usuario.getEmail(),
				usuario.getPassword(),
				mapearAutoridadesRoles(usuario.getRoles())
		);
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.collect(Collectors.toList());
	}

	@Override
	public List<Usuario> listarUsuarios() {
		return usuarioRepository.findAll();
	}
}
