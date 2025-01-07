package com.inventario.inventario.service;

import com.inventario.inventario.enums.TipoTransaccion;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.IngresoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class IngresoService {

    @Autowired
    private IngresoRepository ingresoRepository;
    @Autowired UsuarioServiceImpl usuarioService;

    public List<Ingreso> obtenerTodosLosIngresos() {
        return ingresoRepository.findAllByOrderByIdDesc();
    }

    public Optional<Ingreso> obtenerIngresoPorId(Long id) {
        return ingresoRepository.findById(id);
    }

    public BigDecimal obtenerIngresosDelMes(LocalDate inicio, LocalDate fin) {


        return ingresoRepository.sumarIngresosEntreFechas(inicio, fin)
                .orElse(BigDecimal.ZERO);
    }

    public Ingreso guardarIngreso(Ingreso ingreso) {

        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
        ingreso.setUsuario(usuario);
        ingreso.setTipo(TipoTransaccion.INGRESO);

        return ingresoRepository.save(ingreso);
    }

    public void eliminarIngreso(Long id) {
        ingresoRepository.deleteById(id);
    }
}