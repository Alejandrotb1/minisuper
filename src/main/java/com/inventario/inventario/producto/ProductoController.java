package com.inventario.inventario.producto;

import com.inventario.inventario.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ProductoController {
    @Autowired
    private  ProductoService productoService;
    @Autowired
    private  CategoriaRepository categoriaRepository;
    @Autowired
    private  ProductoRepository productoRepository;

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
        model.addAttribute("listaProductos", new Producto());
        model.addAttribute("listaProductosMostrar", listaProductos);
        // Agregar las categorías disponibles al modelo
        model.addAttribute("listaCategorias", categoriaRepository.findAll());
        return "tablaProductos"; // Vista del formulario de creación
    }


    @PostMapping("/productos/guardar")
    public String guardarProducto(@ModelAttribute("listaProductos") Producto producto) {
        // Guardar el producto utilizando el servicio
        productoService.guardarProducto(producto);
        // Redirigir a la lista de productos después de guardar el nuevo producto
        return "redirect:/productos";
    }
}
