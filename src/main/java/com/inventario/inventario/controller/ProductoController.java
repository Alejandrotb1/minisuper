package com.inventario.inventario.controller;

import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.CategoriaRepository;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.CategoriaService;
import com.inventario.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private  ProductoService productoService;
    @Autowired
    private CategoriaService categoriaService;

    /*public ProductoController(ProductoService productoService, CategoriaRepository categoriaRepository) {
        this.productoService = productoService;
        this.categoriaRepository = categoriaRepository;
    }*/

    @GetMapping
    public String listarProductos(Model model) {
        if (model.containsAttribute("producto")) {
            Producto producto = (Producto) model.asMap().get("producto");
            model.addAttribute("producto", producto);
        } else {
            model.addAttribute("producto", new Producto());
        }
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("categorias", categoriaService.listarCategoriasOrdenadas());
        return "productos";
    }

    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {
        productoService.guardarProducto(producto);
        return "redirect:/productos";
    }

    @GetMapping("/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.buscarProductoPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("categorias", categoriaService.listarCategoriasOrdenadas());
        return "productos";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.eliminarProductoPorId(id);
        return "redirect:/productos";
    }
}

