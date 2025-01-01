package com.inventario.inventario.service;


import com.inventario.inventario.controller.dto.DetalleVentasDTO;
import com.inventario.inventario.controller.dto.VentaConIngresoDTO;
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

    public List<Venta> obtenerTodasLosVentasRelaciones() {
        return ventaRepository.obtenerTodasLasVentasConRelaciones();
    }

    public Optional<Venta> obtenerVentaPorId(Long id) {
        return ventaRepository.findById(id);
    }


    @Transactional
//    public Ingreso guardarIngresoConVentaYDetalles(Ingreso ingreso, Venta venta, VentaConIngresoDTO ventaConIngresoDTO) {
    public void guardarIngresoConVentaYDetalles(VentaConIngresoDTO ventaConIngresoDTO) {


        Ingreso ingreso = new Ingreso();
        Venta venta = new Venta();
        Usuario usuario = usuarioService.obtenerUsuarioAutenticado();

        ingreso.setUsuario(usuario);

        ingreso.setTipo(TipoTransaccion.VENTAS);
        ingreso.setConcepto("Ventas");

        ingreso.setMetodoPago(ventaConIngresoDTO.getIngreso().getMetodoPago());
        ingreso.setMonto(ventaConIngresoDTO.getIngreso().getMonto());


        if (ventaConIngresoDTO.getVenta().getClienteId() != null) {
            venta.setCliente(clienteService.buscarClientePorId(ventaConIngresoDTO.getVenta().getClienteId()));
        }


        Ingreso ingresoGuardado = ingresoRepository.save(ingreso);
        System.out.println("ingreso guardado");

        venta.setIngreso(ingresoGuardado);
        System.out.println("ventaingreso");
        Venta ventaGuardada = ventaRepository.save(venta);
        System.out.println("venta guardada");






        for (DetalleVentasDTO detalle : ventaConIngresoDTO.getVenta().getDetalleVentas()) {
            DetalleVenta detalleVenta = new DetalleVenta();
            Producto producto = productoService.buscarProductoPorId(detalle.getProductoId());





            detalleVenta.setProducto(producto);

//            detalleVenta.setProducto(productoService.buscarProductoPorId(detalle.getProductoId()));

            detalleVenta.setCantidad(detalle.getCantidad());
            detalleVenta.setPrecioUnitario(detalle.getPrecio_unitario());
            detalleVenta.setSubtotal(detalle.getSubtotal());


            productoService.actualizarStock(detalle.getProductoId(), detalle.getCantidad());

            // Asocia el detalle con la venta
            detalleVenta.setVenta(ventaGuardada);

            // Guarda el detalle de la venta
            detalleVentaRepository.save(detalleVenta);
        }

    }

    public void eliminarVenta(Long id) {
        ingresoRepository.deleteById(id);
    }
}