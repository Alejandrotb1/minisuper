package com.inventario.inventario.repository;

import com.inventario.inventario.model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository extends JpaRepository<Proveedor, Integer> {
    boolean existsByNombre(String nombre);
    boolean existsByEmail(String email);


}
