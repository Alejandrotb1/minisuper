package com.inventario.inventario.controller;

import com.inventario.inventario.controller.dto.UsuarioRegistroDTO;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.service.UsuarioService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping({"/registro", "/registrar"})
public class RegistroUsuarioControlador {

	private UsuarioService usuarioService;

	public RegistroUsuarioControlador(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@ModelAttribute("usuario")
	public UsuarioRegistroDTO retornarNuevoUsuarioRegistroDTO() {
		return new UsuarioRegistroDTO();
	}

	@GetMapping
	public String mostrarFormularioDeRegistro(Model model) {
		// Obtener la lista de usuarios desde el servicio
		List<Usuario> listaUsuarios = usuarioService.obtenerTodosLosUsuarios();

		// Convertir la lista de usuarios a DTOs
		List<UsuarioRegistroDTO> usuarioRegistroDTOList = listaUsuarios.stream()
				.map(usuario -> new UsuarioRegistroDTO(
						usuario.getId(),
						usuario.getNombre(),
						usuario.getApellido(),
						usuario.getEmail(),
						null, // Contraseña no se pasa por razones de seguridad
						new ArrayList<>(usuario.getRoles())) // Convertir roles a lista
				)
				.collect(Collectors.toList());

		// Agregar la lista de DTOs al modelo
		model.addAttribute("usuarioRegistroDTOList", usuarioRegistroDTOList);

		return "register1"; // Retorna la vista de registro
	}







	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UsuarioRegistroDTO registroDTO) {
		usuarioService.guardar(registroDTO);
		return "redirect:/registro?exito";
	}


}
