package com.inventario.inventario.service;

import com.inventario.inventario.enums.TipoTransaccion;
import com.inventario.inventario.model.Gasto;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.GastoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GastoService {

    @Autowired
    private GastoRepository gastoRepository;
    @Autowired UsuarioServiceImpl usuarioService;

    public List<Gasto> obtenerTodosLosGastos() {
        return gastoRepository.findAll();
    }

    public Optional<Gasto> obtenerGastoPorId(Long id) {
        return gastoRepository.findById(id);
    }

    public Gasto guardarGasto(Gasto gasto) {

        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
        gasto.setUsuario(usuario);
        gasto.setTipo(TipoTransaccion.GASTO);

        return gastoRepository.save(gasto);
    }

    public void eliminarGasto(Long id) {
        gastoRepository.deleteById(id);
    }
}