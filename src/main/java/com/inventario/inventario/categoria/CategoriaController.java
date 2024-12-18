package com.inventario.inventario.categoria;


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
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categorias")
    public String listarCategorias(Model model){
        List<Categoria> listaCategorias = categoriaRepository.findAll();
        model.addAttribute("listaCategorias", listaCategorias);
        return "tablaCategorias";
    }


    @PostMapping("/categorias/guardar")
    public String agregarCategoria(
            @RequestParam("nombre") String nombre,
            RedirectAttributes redirectAttributes) {

        try {
            Categoria nuevaCategoria = new Categoria();
            nuevaCategoria.setNombre(nombre);
            categoriaRepository.save(nuevaCategoria);
            return "redirect:/categorias"; // Redirige para recargar la tabla
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "La categoría "+nombre+" ya existe.");
            redirectAttributes.addFlashAttribute("nombreDuplicado", nombre);
            return "redirect:/categorias"; // Redirige a la tabla para mostrar el error
        }
    }



}
