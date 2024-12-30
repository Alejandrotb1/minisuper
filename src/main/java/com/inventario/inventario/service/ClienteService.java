package com.inventario.inventario.service;

import com.inventario.inventario.model.Cliente;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }




    public Cliente buscarClientePorNit(String ciNit) {
        Cliente cliente = clienteRepository.findByCiNit(ciNit);
        if (cliente == null) {
            throw new EntityNotFoundException("Cliente no encontrado con el ci_nit: " + ciNit);
        }
        return cliente;
    }



    public List<Cliente> obtenerTodos() {
        return clienteRepository.findAll();
    }

    public Cliente buscarClientePorId(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado con ID: " + id));
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }


    public Cliente guardar(Cliente cliente) {
        return clienteRepository.save(cliente);
    }


    public void eliminar(Long id) {
        clienteRepository.deleteById(id);
    }
}
