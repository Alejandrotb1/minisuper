package com.inventario.inventario.repository;


import com.inventario.inventario.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer > {

    boolean existsByNombre(String nombre);
}
