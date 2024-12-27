package com.inventario.inventario.service;

import com.inventario.inventario.model.Categoria;
import com.inventario.inventario.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public void guardarCategoria(String nombre) throws DataIntegrityViolationException {
        Categoria nuevaCategoria = new Categoria();
        nuevaCategoria.setNombre(nombre);
        categoriaRepository.save(nuevaCategoria);
    }
    public List<Categoria> listarCategoriasOrdenadas() {
        return categoriaRepository.findAll().stream()
                .sorted(Comparator.comparing(Categoria::getNombre)) // ordenar por nombre (alfabéticamente)
                .collect(Collectors.toList());
    }
}
