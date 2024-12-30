package com.inventario.inventario.controller;


import com.inventario.inventario.controller.dto.ProductoDTO;
import com.inventario.inventario.controller.dto.WrapperVentasDTO;
import com.inventario.inventario.model.DetalleVenta;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.model.Venta;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.ClienteService;
import com.inventario.inventario.service.GastoService;
import com.inventario.inventario.service.IngresoService;
import com.inventario.inventario.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String Venta(Model model) {

                model.addAttribute("productos", productoRepository.findAll());
                model.addAttribute("clientes",clienteService.obtenerTodos());


        WrapperVentasDTO wrapperVentasDTO = new WrapperVentasDTO();
        wrapperVentasDTO.setVenta(new Venta());
        wrapperVentasDTO.setIngreso(new Ingreso());
//        wrapperVentasDTO.setIngreso(new List<DetalleVenta>());
        model.addAttribute("wrapperVentas", wrapperVentasDTO);
        return "venta";
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