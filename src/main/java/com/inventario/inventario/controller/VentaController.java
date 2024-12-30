package com.inventario.inventario.controller;


import com.inventario.inventario.controller.dto.ProductoDTO;
import com.inventario.inventario.controller.dto.VentaConIngresoDTO;
import com.inventario.inventario.controller.dto.WrapperVentasDTO;
import com.inventario.inventario.model.*;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.ClienteService;
import com.inventario.inventario.service.GastoService;
import com.inventario.inventario.service.IngresoService;
import com.inventario.inventario.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String Venta(Model model, CsrfToken csrfToken) {
        model.addAttribute("_csrf", csrfToken);
                model.addAttribute("productos", productoRepository.findAll());
//                model.addAttribute("clientes",clienteService.obtenerTodos());


        WrapperVentasDTO wrapperVentasDTO = new WrapperVentasDTO();
        wrapperVentasDTO.setVenta(new Venta());
        wrapperVentasDTO.setIngreso(new Ingreso());
//        wrapperVentasDTO.setIngreso(new List<DetalleVenta>());
        model.addAttribute("wrapperVentas", wrapperVentasDTO);
        return "venta";
    }

    @PostMapping("/guardar")
    public String guardarIngresoConVentaYDetalles(@RequestBody VentaConIngresoDTO ventaConIngresoDTO) {
        // Validación de los datos recibidos

        System.out.println("antes");
        if (ventaConIngresoDTO == null || ventaConIngresoDTO.getVenta() == null || ventaConIngresoDTO.getIngreso() == null) {
            return "error"; // Retornar error si falta algún dato esencial
        }
        System.out.println("despues de l if");
        // Obtener el Ingreso y la Venta del DTO
        Ingreso ingreso = new Ingreso();
        ingreso.setMetodoPago(ventaConIngresoDTO.getIngreso().getMetodoPago());
        ingreso.setMonto(ventaConIngresoDTO.getIngreso().getMonto());
        System.out.println("despues de ingreso");
        // Obtener la venta y los detalles
        Venta venta = new Venta();
        System.out.println("despues de iniciar venta");
        System.out.println(venta);



        //error al asignar cliente
//        Cliente cliente = clienteService.buscarClientePorId(ventaConIngresoDTO.getVenta().getClienteId());
////        venta.setCliente(clienteService.obtenerPorId(ventaConIngresoDTO.getVenta().getClienteId()));
////        venta.setClienteId(ventaConIngresoDTO.getVenta().getClienteId());
//        System.out.println("antes de asignar cliente");
//        venta.setCliente(cliente);
//        System.out.println("despues de asignar cliente");
//
//        System.out.println(venta);





        // Guardar el ingreso con la venta y los detalles asociados
        ventaService.guardarIngresoConVentaYDetalles(ingreso, venta, ventaConIngresoDTO.getVenta().getDetalleVentas());

        // Redirigir a la página de ventas o alguna otra respuesta
        return "redirect:/venta"; // O la vista que corresponda
    }




//
//    @PostMapping("/guardar")
//    public String guardarIngresoConVenta(@ModelAttribute("wrapperVentas") WrapperVentasDTO wrapper) {
//        // Asegúrate de que el objeto Venta no sea null
//        if (wrapper.getVenta() == null) {
//            wrapper.setVenta(new Venta()); // Inicializa si es necesario
//        }
//
//        Venta venta = wrapper.getVenta();
//        Ingreso ingreso = wrapper.getIngreso();
//
//        // Ahora, ya puedes invocar setIngreso sin problemas
//        venta.setIngreso(ingreso);
//
//        // Llama al servicio para guardar la transacción
//        ventaService.guardarIngresoConVenta(ingreso, venta);
//
//        return "redirect:/venta";
//    }

//    @PostMapping("/guardar")
//    public String guardarIngresoConVenta(@RequestBody WrapperVentasDTO wrapper) {
//        // Asegúrate de que el objeto Venta no sea null
//        if (wrapper.getVenta() == null) {
//            wrapper.setVenta(new Venta()); // Inicializa si es necesario
//        }
//
//        Venta venta = wrapper.getVenta();
//        Ingreso ingreso = wrapper.getIngreso();
//        List<ProductoDTO> productos = wrapper.getProductos();  // Aquí tomamos la lista de productos
//
//        // Llama al servicio para guardar la transacción
//        ventaService.guardarIngresoConVenta(ingreso, venta, productos);
//
//        return "redirect:/venta";
//    }






}