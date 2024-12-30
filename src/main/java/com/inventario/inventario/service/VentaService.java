package com.inventario.inventario.service;


import com.inventario.inventario.controller.dto.DetalleVentasDTO;
import com.inventario.inventario.controller.dto.ProductoDTO;
import com.inventario.inventario.enums.TipoTransaccion;
import com.inventario.inventario.model.*;
import com.inventario.inventario.repository.DetalleVentaRepository;
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
    @Autowired
    DetalleVentaRepository detalleVentaRepository;
    @Autowired
    ProductoService productoService;

    public List<Venta> obtenerTodosLosVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }


    @Transactional
    public Ingreso guardarIngresoConVentaYDetalles(Ingreso ingreso, Venta venta, List<DetalleVentasDTO> detalles) {
        // 1. Guarda el ingreso y asocia el usuario autenticado
        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
        ingreso.setUsuario(usuario);
        ingreso.setTipo(TipoTransaccion.VENTAS);
        ingreso.setConcepto("Ventas");

        // Guarda el ingreso
        Ingreso ingresoGuardado = ingresoRepository.save(ingreso);
        System.out.println("ingreso guardado");

        // 2. Asocia la venta con el ingreso guardado
        venta.setIngreso(ingresoGuardado);
        Venta ventaGuardada = ventaRepository.save(venta);
        System.out.println("venta guardada");
        // 3. Asocia cada detalle de venta con la venta guardada
        for (DetalleVentasDTO detalle : detalles) {
            DetalleVenta detalleVenta = new DetalleVenta();
            Producto producto = productoService.buscarProductoPorId(detalle.getProductoId());

            // Asignar el producto al detalle de venta
            detalleVenta.setProducto(producto);

//            detalleVenta.setProducto(productoService.buscarProductoPorId(detalle.getProductoId()));
//            detalleVenta.setProductoId(detalle.getProductoId());
            detalleVenta.setCantidad(detalle.getCantidad());
            detalleVenta.setPrecioUnitario(detalle.getPrecio_unitario());
            detalleVenta.setSubtotal(detalle.getSubtotal());

            // Asocia el detalle con la venta
            detalleVenta.setVenta(ventaGuardada);

            // Guarda el detalle de la venta
            detalleVentaRepository.save(detalleVenta);
        }

        // 4. Retorna el ingreso guardado (o puedes retornar cualquier otra entidad si lo prefieres)
        return ingresoGuardado;
    }





//    public Venta guardarVenta(Venta venta) {d
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


//    @Transactional
//    public Ingreso guardarIngresoConVenta(Ingreso ingreso, Venta venta) {
//        // Asocia el ingreso con el usuario autenticado
//        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
//        ingreso.setUsuario(usuario);
//        ingreso.setTipo(TipoTransaccion.VENTAS); // o el tipo adecuado
//
//        // Guarda el ingreso primero
//        Ingreso ingresoGuardado = ingresoRepository.save(ingreso);
//
//        // Ahora la venta puede ser asociada
//        venta.setIngreso(ingresoGuardado);
//
//        // Si ya tienes un cliente para la venta, asócialo también
//        Optional<Cliente> cliente = clienteService.obtenerPorId(1L); // ejemplo, obtener cliente
//        cliente.ifPresent(venta::setCliente);
//
//        // Guarda la venta con el ingreso asociado
//        ventaRepository.save(venta); // Se guarda la venta y gracias a la cascada, se guardará el ingreso
//
//        // Retorna el ingreso que ahora tiene la venta asociada
//        return ingresoGuardado;
//    }


//    @Transactional
//    public Ingreso guardarIngresoConVenta(Ingreso ingreso, Venta venta, List<ProductoDTO> productos) {
//        // Asocia el ingreso con el usuario autenticado
//        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();
//        ingreso.setUsuario(usuario);
//        ingreso.setTipo(TipoTransaccion.VENTAS); // o el tipo adecuado
//
//        // Guarda el ingreso primero
//        Ingreso ingresoGuardado = ingresoRepository.save(ingreso);
//
//        // Ahora la venta puede ser asociada
//        venta.setIngreso(ingresoGuardado);
//
//        // Si ya tienes un cliente para la venta, asócialo también
//        Optional<Cliente> cliente = clienteService.obtenerPorId(1L); // ejemplo, obtener cliente
//        cliente.ifPresent(venta::setCliente);
//
//        // Guarda la venta con el ingreso asociado
//        ventaRepository.save(venta); // Se guarda la venta y gracias a la cascada, se guardará el ingreso
//
//        // Ahora, maneja los productos
//        for (ProductoDTO productoDTO : productos) {
//            Producto producto = productoService.buscarProductoPorId(productoDTO.getId());
//            // Crear la relación entre la venta y los productos, ajustando cantidades y precios
//            DetalleVenta detalleVenta = new DetalleVenta();
//            detalleVenta.setProducto(producto);
//            detalleVenta.setCantidad(productoDTO.getProductQuantity());
//            detalleVenta.setPrecioUnitario(productoDTO.getProductPrice());
//            detalleVenta.setSubtotal(productoDTO.getSubtotal());
//            detalleVenta.setVenta(venta); // Asociamos el detalle con la venta
//
//            // Guardar el detalle de la venta
//            detalleVentaRepository.save(detalleVenta);
//
//        }
//
//        // Retorna el ingreso que ahora tiene la venta asociada
//        return ingresoGuardado;
//    }



    public void eliminarVenta(Long id) {
        ingresoRepository.deleteById(id);
    }
}