package com.inventario.inventario.controller;

import com.inventario.inventario.model.Gasto;
import com.inventario.inventario.service.GastoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/gastos")
public class GastoController {

    @Autowired
    private GastoService gastoService;

    @GetMapping
    public String listarGastos(Model model) {
        List<Gasto> gastos = gastoService.obtenerTodosLosGastos();
        model.addAttribute("gastos", gastos);

        model.addAttribute("gasto", new Gasto());
        return "gastos"; // Plantilla Thymeleaf para listar gastos
    }

//    @GetMapping("/nuevo")
//    public String mostrarFormularioGasto(Model model) {
//        model.addAttribute("gasto", new Gasto());
//        return "gastos/formulario"; // Plantilla Thymeleaf para el formulario
//    }
//
    @PostMapping("/guardar")
    public String guardarGasto(@ModelAttribute("gasto") Gasto gasto) {
        gastoService.guardarGasto(gasto);
        return "redirect:/gastos";
    }
//
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        List<Gasto> gastos = gastoService.obtenerTodosLosGastos();
        model.addAttribute("gastos", gastos);


        Optional<Gasto> gasto = gastoService.obtenerGastoPorId(id);
        if (gasto.isPresent()) {
            model.addAttribute("gasto", gasto.get());
            return "/gastos";
        }
        return "redirect:/gastos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarGasto(@PathVariable Long id) {
        gastoService.eliminarGasto(id);
        return "redirect:/gastos";
    }
}