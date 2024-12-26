package com.inventario.inventario.controller;


import com.inventario.inventario.model.Producto;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private GastoService gastoService;
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
        return "/venta"; // Plantilla Thymeleaf para listar gastos
    }
}