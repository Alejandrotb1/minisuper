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
public class ProductSeeder implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {

    }
//
//    private final ProductoRepository productoRepository;
//    private final CategoriaRepository categoriaRepository;
//    private final Faker faker;
//
//    public ProductSeeder(ProductoRepository productoRepository, CategoriaRepository categoriaRepository) {
//        this.productoRepository = productoRepository;
//        this.categoriaRepository = categoriaRepository;
//        this.faker = new Faker();
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.out.println("Iniciando ProductSeeder...");
//
//        // Obtiene todas las categorías existentes
//        var categorias = categoriaRepository.findAll();
//
//        if (!categorias.isEmpty()) {
//            // Inserta 1000 productos de prueba
//            for (int i = 0; i < 15; i++) {
//                Producto producto = new Producto();
//                producto.setCodigo(faker.commerce().promotionCode());
//                producto.setNombre(faker.commerce().productName());
//                producto.setPrecioCompra(BigDecimal.valueOf(faker.number().randomDouble(2, 10, 500)));
//                producto.setPrecioVenta(BigDecimal.valueOf(faker.number().randomDouble(2, 15, 800)));
//                producto.setCategoria(categorias.get(faker.number().numberBetween(0, categorias.size()))); // Asigna una categoría aleatoria
//                producto.setImagen(faker.internet().image());
//                producto.setDescripcion(faker.lorem().sentence());
//                producto.setStock(faker.number().numberBetween(1, 500));  // Cantidad de productos aleatoria
//
//                productoRepository.save(producto); // Guarda el producto en la base de datos
//                System.out.println("Producto guardado: " + producto.getNombre());
//            }
//        } else {
//            System.out.println("No hay categorías disponibles, asegúrese de tener categorías insertadas primero.");
//        }
//
//        System.out.println("Datos de productos insertados con Faker.");
//    }
}
