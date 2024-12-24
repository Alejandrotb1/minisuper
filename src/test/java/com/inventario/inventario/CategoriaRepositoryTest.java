/*
package com.inventario.inventario;

import com.inventario.inventario.categoria.Categoria;
import com.inventario.inventario.repository.CategoriaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class CategoriaRepositoryTest {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    public void testCrearCategoria(){
        Categoria categoriaGuardada =  categoriaRepository.save(new Categoria("bebida"));
        assertThat(categoriaGuardada.getId()).isGreaterThan(0);
    }
}
*/
