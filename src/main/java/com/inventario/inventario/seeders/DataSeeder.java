package com.inventario.inventario.seeders;

import com.inventario.inventario.model.Categoria;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.CategoriaRepository;
import com.inventario.inventario.repository.ProductoRepository;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataSeeder implements CommandLineRunner {

    private final CategoriaRepository categoriaRepository;
    private final ProductoRepository productoRepository;
    private final Faker faker;

    public DataSeeder(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {
        this.categoriaRepository = categoriaRepository;
        this.productoRepository = productoRepository;
        this.faker = new Faker();
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Iniciando DataSeeder...");

        // Insertar 10 categorías
        if (categoriaRepository.count() == 0) {
            for (int i = 0; i < 10; i++) {
                Categoria categoria = new Categoria();
                categoria.setNombre(faker.commerce().department());  // Nombre de la categoría aleatorio
                categoria.setDescripcion(faker.lorem().sentence());  // Descripción aleatoria
                categoriaRepository.save(categoria);  // Guardar categoría
                System.out.println("Categoría guardada: " + categoria.getNombre());
            }
            System.out.println("10 categorías insertadas con Faker.");
        } else {
            System.out.println("Ya existen categorías en la base de datos.");
        }

        // Obtener todas las categorías existentes
        var categorias = categoriaRepository.findAll();

        // Insertar 100 productos si no existen productos en la base de datos
        if (productoRepository.count() == 0) {
            for (int i = 0; i < 100; i++) {
                Producto producto = new Producto();
                producto.setCodigo(faker.commerce().promotionCode());  // Código aleatorio
                producto.setNombre(faker.commerce().productName());  // Nombre del producto aleatorio
                producto.setPrecioCompra(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 500)));  // Precio de compra
                producto.setPrecioVenta(BigDecimal.valueOf(faker.number().randomDouble(2, 15, 800)));  // Precio de venta
                producto.setCategoria(categorias.get(faker.number().numberBetween(0, categorias.size())));  // Asigna una categoría aleatoria
                producto.setImagen(faker.internet().image());  // Imagen aleatoria
                producto.setDescripcion(faker.lorem().sentence());  // Descripción aleatoria
                producto.setStock(faker.number().numberBetween(1, 500));  // Stock aleatorio

                productoRepository.save(producto);  // Guardar producto
                System.out.println("Producto guardado: " + producto.getNombre());
            }
            System.out.println("100 productos insertados con Faker.");
        } else {
            System.out.println("Ya existen productos en la base de datos.");
        }
    }
}
