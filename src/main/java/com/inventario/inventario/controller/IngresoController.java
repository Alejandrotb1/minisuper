package com.inventario.inventario.controller;

import com.inventario.inventario.model.Ingreso;
import com.inventario.inventario.service.IngresoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/ingresos")
public class IngresoController {

    @Autowired
    private IngresoService ingresoService;

    @GetMapping
    public String listarIngresos(Model model) {
        List<Ingreso> ingresos = ingresoService.obtenerTodosLosIngresos();
        model.addAttribute("ingresos", ingresos);

        model.addAttribute("ingreso", new Ingreso());
        return "ingresos"; // Plantilla Thymeleaf para listar ingresos
    }

//    @GetMapping("/nuevo")
//    public String mostrarFormularioIngreso(Model model) {
//        model.addAttribute("ingreso", new Ingreso());
//        return "ingresos/formulario"; // Plantilla Thymeleaf para el formulario
//    }
//
    @PostMapping("/guardar")
    public String guardarIngreso(@ModelAttribute("ingreso") Ingreso ingreso) {
        ingresoService.guardarIngreso(ingreso);
        return "redirect:/ingresos";
    }
//
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        List<Ingreso> ingresos = ingresoService.obtenerTodosLosIngresos();
        model.addAttribute("ingresos", ingresos);


        Optional<Ingreso> ingreso = ingresoService.obtenerIngresoPorId(id);
        if (ingreso.isPresent()) {
            model.addAttribute("ingreso", ingreso.get());
            return "/ingresos";
        }
        return "redirect:/ingresos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarIngreso(@PathVariable Long id) {
        ingresoService.eliminarIngreso(id);
        return "redirect:/ingresos";
    }
}