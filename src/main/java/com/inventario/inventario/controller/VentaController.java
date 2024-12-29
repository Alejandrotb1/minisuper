package com.inventario.inventario.controller;


import com.inventario.inventario.controller.dto.WrapperVentasDTO;
import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.model.Producto;
import com.inventario.inventario.model.Venta;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.GastoService;
import com.inventario.inventario.service.IngresoService;
import com.inventario.inventario.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;
    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping
    public String Venta(Model model) {

        List<Producto> listaProductos = productoRepository.findAll();
                model.addAttribute("productos", listaProductos);

//        List<Gasto> gastos = gastoService.obtenerTodosLosGastos();
//        model.addAttribute("gastos", gastos);
//
//        model.addAttribute("gasto", new Gasto());




        WrapperVentasDTO wrapperVentasDTO = new WrapperVentasDTO();
        wrapperVentasDTO.setVenta(new Venta());
        wrapperVentasDTO.setIngreso(new Ingreso());
        model.addAttribute("wrapperVentas", wrapperVentasDTO);
        return "venta";
    }





    @PostMapping("/guardar")
    public String guardarIngresoConVenta(@ModelAttribute("wrapperVentas") WrapperVentasDTO wrapper) {
        // Asegúrate de que el objeto Venta no sea null
        if (wrapper.getVenta() == null) {
            wrapper.setVenta(new Venta()); // Inicializa si es necesario
        }

        Venta venta = wrapper.getVenta();
        Ingreso ingreso = wrapper.getIngreso();

        // Ahora, ya puedes invocar setIngreso sin problemas
        venta.setIngreso(ingreso);

        // Llama al servicio para guardar la transacción
        ventaService.guardarIngresoConVenta(ingreso, venta);

        return "redirect:/venta";
    }




}