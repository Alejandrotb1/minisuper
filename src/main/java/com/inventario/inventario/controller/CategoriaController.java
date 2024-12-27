package com.inventario.inventario.controller;


import com.inventario.inventario.model.Categoria;

import com.inventario.inventario.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categorias")
    public String listarCategorias(Model model) {
        List<Categoria> listaCategorias = categoriaService.listarCategorias();
        model.addAttribute("listaCategorias", listaCategorias);
        return "tablaCategorias";
    }

    @PostMapping("/categorias/guardar")
    public String agregarCategoria(
            @RequestParam("nombre") String nombre,
            RedirectAttributes redirectAttributes) {

        try {
            categoriaService.guardarCategoria(nombre);
            return "redirect:/categorias"; // Redirige para recargar la tabla
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "La categoría " + nombre + " ya existe.");
            redirectAttributes.addFlashAttribute("nombreDuplicado", nombre);
            return "redirect:/categorias"; // Redirige a la tabla para mostrar el error
        }
    }
}
