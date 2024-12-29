package com.inventario.inventario.service;


import com.inventario.inventario.enums.TipoTransaccion;
import com.inventario.inventario.model.Cliente;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Venta;
import com.inventario.inventario.model.Usuario;
import com.inventario.inventario.repository.IngresoRepository;
import com.inventario.inventario.repository.VentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;
    @Autowired
    private IngresoRepository ingresoRepository;
    @Autowired UsuarioServiceImpl usuarioService;
    @Autowired
    ClienteService clienteService;

    public List<Venta> obtenerTodosLosVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }

//    public Venta guardarVenta(Venta venta) {
//
//        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
//        venta.setUsuario(usuario);
//        venta.setTipo(TipoTransaccion.VENTAS);
//
//        return ingresoRepository.save(ingreso);
//    }


//    @Transactional
//    public Ingreso guardarIngresoConVenta(Ingreso ingreso, Venta venta) {
//        // Asocia el ingreso con el usuario autenticado
//        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
//
////        Optional<Cliente> cliente = clienteService.obtenerPorId(Long.valueOf(1));
//        ingreso.setUsuario(usuario);
//        ingreso.setTipo(TipoTransaccion.VENTAS);
//        ingreso.setConcepto("ventas");
//
////        venta.setCliente(cliente.get());
//
//        // Establece la relación bidireccional entre ingreso y venta
//        venta.setIngreso(ingreso);
//        ingreso.setVenta(venta);
//
//        // Guarda el ingreso, lo cual guardará automáticamente la venta debido a CascadeType.ALL
//        return ingresoRepository.save(ingreso);
//    }


    @Transactional
    public Ingreso guardarIngresoConVenta(Ingreso ingreso, Venta venta) {
        // Asocia el ingreso con el usuario autenticado
        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
        ingreso.setUsuario(usuario);
        ingreso.setTipo(TipoTransaccion.VENTAS); // o el tipo adecuado

        // Guarda el ingreso primero
        Ingreso ingresoGuardado = ingresoRepository.save(ingreso);

        // Ahora la venta puede ser asociada
        venta.setIngreso(ingresoGuardado);

        // Si ya tienes un cliente para la venta, asócialo también
        Optional<Cliente> cliente = clienteService.obtenerPorId(1L); // ejemplo, obtener cliente
        cliente.ifPresent(venta::setCliente);

        // Guarda la venta con el ingreso asociado
        ventaRepository.save(venta); // Se guarda la venta y gracias a la cascada, se guardará el ingreso

        // Retorna el ingreso que ahora tiene la venta asociada
        return ingresoGuardado;
    }



    public void eliminarVenta(Long id) {
        ingresoRepository.deleteById(id);
    }
}