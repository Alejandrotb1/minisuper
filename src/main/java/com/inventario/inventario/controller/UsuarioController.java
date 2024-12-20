package com.inventario.inventario.controller;

import com.inventario.inventario.enums.Role;
import com.inventario.inventario.model.Rol;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Set;

@Controller
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;



//    @GetMapping("/")
//    public String verPaginaDeInicio(Authentication authentication, Model modelo) {
//        // Obtener el usuario actual
//        Usuario usuario = usuarioService.findByEmail(authentication.getName());
//        modelo.addAttribute("usuario", usuario);  // Pasar el usuario al modelo
//        return "index"; // Se muestra la vista "index"
//    }

//    @PostMapping("/cambiarRol")
//    public String cambiarRol(Authentication authentication, Model modelo) {
//        // Obtener el usuario actual
//        String email = authentication.getName(); // Obtener el email del usuario actual
//
//        // Actualizar roles
//        Usuario usuarioActualizado = usuarioService.actualizarRoles(email);
//
//        // Pasar el usuario actualizado al modelo
//        modelo.addAttribute("usuario", usuarioActualizado);
//        return "index"; // Recargar la página con los nuevos roles
//    }
}
