package com.inventario.inventario.controller;


import com.inventario.inventario.controller.dto.*;
import com.inventario.inventario.model.*;
import com.inventario.inventario.repository.DetalleVentaRepository;
import com.inventario.inventario.repository.ProductoRepository;
import com.inventario.inventario.service.ClienteService;
import com.inventario.inventario.service.GastoService;
import com.inventario.inventario.service.IngresoService;
import com.inventario.inventario.service.VentaService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import org.springframework.security.web.csrf.CsrfToken;

import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import java.util.ArrayList;
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
    @Autowired
    private IngresoService ingresoService;
    @Autowired
    private DetalleVentaRepository detalleVentaRepository;


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

    @GetMapping("/ventas")
    public String ListaVentas(Model model) {
        model.addAttribute("ventas", ventaService.obtenerTodosLosVentas());

        return "ventas";  // Nombre de la vista
    }
    @GetMapping("/detalleVentas")
    public String ListaDetalleVenta(Model model) {
        model.addAttribute("detalleVenta", detalleVentaRepository.findAll());

        return "detalleVentas";  // Nombre de la vista
    }




    @PostMapping("/guardar")
    public String guardarIngresoConVentaYDetalles(@RequestBody VentaConIngresoDTO ventaConIngresoDTO,  @RequestHeader("X-CSRF-TOKEN") String csrfToken) {

//        System.out.println("token recibido: " + csrfToken);

        if (ventaConIngresoDTO == null || ventaConIngresoDTO.getVenta() == null || ventaConIngresoDTO.getIngreso() == null) {
            return "error"; //error si falta algún dato
        }

        ventaService.guardarIngresoConVentaYDetalles(ventaConIngresoDTO);




        return "redirect:/venta";
    }






}