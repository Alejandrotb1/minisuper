package com.inventario.inventario.controller;

import com.inventario.inventario.model.Proveedor;
import com.inventario.inventario.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorService;

    @GetMapping("/proveedores")
    public String getProveedores(Model model) {
        List<Proveedor> proveedorList = proveedorService.obtenerTodosLosProveedores();

        if (model.containsAttribute("proveedor")) {
            Proveedor proveedor = (Proveedor) model.asMap().get("proveedor");
            model.addAttribute("proveedor", proveedor);
        } else {
            model.addAttribute("proveedor", new Proveedor());
        }

        model.addAttribute("proveedorList", proveedorList);
        return "proveedores";
    }

    @PostMapping("/proveedores/guardar")
    public String agregarProveedor(@ModelAttribute("proveedor") Proveedor proveedor, RedirectAttributes redirectAttributes) {
        return proveedorService.guardarProveedor(proveedor, redirectAttributes);
    }

    @GetMapping("/proveedores/editar/{id}")
    public String editarProveedor(@PathVariable("id") Integer id,Model model, RedirectAttributes redirectAttributes) {
        Proveedor proveedor = proveedorService.buscarProveedorPorId(id);
        model.addAttribute("proveedor", proveedor);
        model.addAttribute("proveedorList", proveedorService.obtenerTodosLosProveedores());

        return "proveedores";
    }

    @PostMapping("/proveedores/eliminar/{id}")
    public String eliminarProveedor(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        proveedorService.eliminarProveedor(id, redirectAttributes);
        return "redirect:/proveedores";
    }


}



