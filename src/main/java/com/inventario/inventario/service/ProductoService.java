package com.inventario.inventario.service;


import com.inventario.inventario.model.Categoria;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.CategoriaRepository;
import com.inventario.inventario.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoService(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    public Producto buscarProductoPorId(Integer productoId) {
        return productoRepository.findById(productoId)
                .orElseThrow(() -> new IllegalArgumentException("Producto no encontrado con ID: " + productoId));
    }

    public void guardarProducto(Producto producto) {



        // Si el precio de venta no ha sido asignado, lo calculamos como un 30% más del precio de compra
        if (producto.getPrecioVenta() == null || producto.getPrecioVenta().compareTo(producto.getPrecioCompra().multiply(new BigDecimal("1.30"))) < 0) {
            producto.setPrecioVenta(producto.getPrecioCompra().multiply(new BigDecimal("1.30")));
        }


//        Producto productoExistente = productoRepository.findById(producto.getId()).orElse(null);

        if (producto.getId() != null) {
            producto.setStock(producto.getStock() + producto.getCantidad());
        } else {

            producto.setStock(producto.getCantidad());
        }

        productoRepository.save(producto);
    }

    public Categoria obtenerCategoriaPorId(Integer categoriaId) {
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new IllegalArgumentException("Categoría no encontrada con ID: " + categoriaId));
    }
}
