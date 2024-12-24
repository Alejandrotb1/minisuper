package com.inventario.inventario.controller;

import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.CategoriaRepository;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private ProductoService productoService;
    @Autowired
    private  CategoriaRepository categoriaRepository;
    @Autowired
    private ProductoRepository productoRepository;

/*
//sin autowired, constructor
public ProductoController(ProductoService productoService, CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
    }*/

 /*   @GetMapping("/productos")
    public String nuevoProducto(Model model) {
        // Crear un nuevo objeto Producto vacío
        model.addAttribute("listaProductos", new Producto());
        // Agregar las categorías disponibles al modelo
        model.addAttribute("listaCategorias", categoriaRepository.findAll());
        return "tablaProductos"; // Vista del formulario de creación
    }*/

    @GetMapping("/productos")
    public String nuevoProducto(Model model) {
        // Crear un nuevo objeto Producto vacío
        List<Producto> listaProductos = productoRepository.findAll();
        model.addAttribute("producto", new Producto());
        model.addAttribute("productos", listaProductos);
        // Agregar las categorías disponibles al modelo
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "productos";
    }


    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute("producto") Producto producto) {

        productoService.guardarProducto(producto);

        return "redirect:/productos";
    }
    @GetMapping("/productos/editar/{id}")
    public String editarProducto(@PathVariable("id") Integer id, Model model){
        Producto producto = productoRepository.findById(id).get();
        model.addAttribute("producto", producto);


        List<Producto> listaProductos = productoRepository.findAll();

        model.addAttribute("productos", listaProductos);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "productos";

    }

    @GetMapping
    public String eliminarProducto(@PathVariable("id") Integer id, Model model){
        productoRepository.deleteById(id);
        return "productos";
    }

}
