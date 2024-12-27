package com.inventario.inventario.service;


import com.inventario.inventario.model.Categoria;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.CategoriaRepository;
import com.inventario.inventario.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ProductoService {

    @Autowired
    private  ProductoRepository productoRepository;
    @Autowired
    private  CategoriaRepository categoriaRepository;


/*    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;*/

/*
    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }
*/

    public Producto buscarProductoPorId(Integer productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));
    }

    public void guardarProducto(Producto producto) {
        // Si el precio de venta no ha sido asignad
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta().compareTo(producto.getPrecioCompra().multiply(new BigDecimal("1.30"))) < 0) {
            producto.setPrecioVenta(producto.getPrecioCompra().multiply(new BigDecimal("1.30")));
        }

        if (producto.getId() != null) {
            Producto productoExistente = buscarProductoPorId(producto.getId());
            producto.setStock(productoExistente.getStock() + producto.getCantidad());
        } else {
            producto.setStock(producto.getCantidad());
        }

        productoRepository.save(producto);
    }

    public Categoria obtenerCategoriaPorId(Integer categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + categoriaId));
    }

    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    public void eliminarProductoPorId(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw new IllegalArgumentException("Producto no encontrado con ID: " + id);
        }
        productoRepository.deleteById(id);
    }

}