package com.inventario.inventario.repository;

import com.inventario.inventario.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {



//    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock < 5")
//    long countProductosConStockCritico();

    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock > 0 AND p.stock <= 5")
    long countProductosConStockCritico();

    @Query("SELECT COUNT(p) FROM Producto p WHERE p.stock = 0")
    long countProductosSinStock();
}
