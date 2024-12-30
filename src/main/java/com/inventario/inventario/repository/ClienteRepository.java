package com.inventario.inventario.repository;

import com.inventario.inventario.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    Cliente findByCiNit(String ciNit);
//    @Query("SELECT c FROM Cliente c WHERE c.ci_nit = :ciNit")
//    Cliente findByCiNit(@Param("ciNit") String ciNit);

}

