package com.inventario.inventario.service;

import com.inventario.inventario.controller.dto.UsuarioRegistroDTO;
import com.inventario.inventario.enums.Role;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
		if (usuarioRepository.findByEmail(registroDTO.getEmail()) != null) {
			throw new IllegalArgumentException("El email ya está registrado.");
		}

		// Ya tenemos los roles como una lista de Role, no es necesario mapearlos
		Set<Role> roles = new HashSet<>(registroDTO.getRoles());

		Usuario usuario = new Usuario();
		usuario.setNombre(registroDTO.getNombre());
		usuario.setApellido(registroDTO.getApellido());
		usuario.setEmail(registroDTO.getEmail());
		usuario.setPassword(passwordEncoder.encode(registroDTO.getPassword()));
		usuario.setRoles(roles);

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
				usuario.getRoles().stream()
						.map(role -> new org.springframework.security.core.authority.SimpleGrantedAuthority(role.name()))
						.collect(Collectors.toSet())
		);
	}



	@Override
	public List<Usuario> obtenerTodosLosUsuarios() {
		return usuarioRepository.findAll(); // Devuelve todos los usuarios
	}














	// Método para crear un usuario administrador al iniciar la aplicación
	@PostConstruct
	private void crearAdmin() {
		if (usuarioRepository.findByEmail("admin@inventario.com") == null) {
			Usuario admin = new Usuario();
			admin.setNombre("Administrador");
			admin.setApellido("Principal");
			admin.setEmail("admin@inventario.com");
			admin.setPassword(passwordEncoder.encode("admin123"));
			admin.setRoles(Set.of(Role.ROLE_ADMIN));

			usuarioRepository.save(admin);
		}
	}
}
