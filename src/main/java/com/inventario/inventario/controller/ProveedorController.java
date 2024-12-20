package com.inventario.inventario.controller;


import com.inventario.inventario.model.Proveedor;
import com.inventario.inventario.repository.ProveedorRepository;
import com.inventario.inventario.service.ProveedorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ProveedorController {

   @Autowired
   private ProveedorRepository proveedorRepository;


/*
    @GetMapping("/proveedores")
    public String  getProveedores(Model model){

        List<Proveedor> proveedorList = proveedorRepository.findAll();
        model.addAttribute("proveedor", new Proveedor());
        model.addAttribute("proveedorList", proveedorList);
        if (model.containsAttribute("proveedor")) {
            model.addAttribute("proveedor", model.asMap().get("proveedor"));
        }
        return "proveedores";
    }
*/
@GetMapping("/proveedores")
public String getProveedores(Model model) {

    List<Proveedor> proveedorList = proveedorRepository.findAll();



    if (model.containsAttribute("proveedor")) {
        Proveedor proveedor = (Proveedor) model.asMap().get("proveedor");
        System.out.println("Proveedor en GET: " + proveedor.getNombre());  // Verifica que el proveedor tiene datos
        model.addAttribute("proveedor", proveedor);
    }
    else{
        model.addAttribute("proveedor", new Proveedor());
    }
    model.addAttribute("proveedorList", proveedorList);
    return "proveedores";
}


/*
    @PostMapping("/proveedores/guardar")
    public String agregarCategoria(
            @RequestParam("nombre") String nombre,
            RedirectAttributes redirectAttributes, @ModelAttribute("proveedor") Proveedor proveedor) {

        try {
            Proveedor nuevoProveedor = new Proveedor();
            nuevoProveedor.setNombre(nombre);
            proveedorRepository.save(nuevoProveedor);
            return "redirect:/proveedores";
        } catch (DataIntegrityViolationException e) {
            redirectAttributes.addFlashAttribute("error", "El proveedor "+nombre+" ya existe.");
            redirectAttributes.addFlashAttribute("nombreDuplicado", nombre);
            return "redirect:/proveedores";
        }
    }*/


    @Autowired
    private ProveedorService proveedorService;


    @PostMapping("proveedores/guardar")
    public String agregarProveedor(@ModelAttribute("proveedor") Proveedor proveedor, RedirectAttributes redirectAttributes) {
        // Llamar al servicio para guardar el proveedor

        System.out.println("Proveedor recibido: " + proveedor.getNombre());

        redirectAttributes.addFlashAttribute("proveedor", proveedor);

        return proveedorService.guardarProveedor(proveedor, redirectAttributes);

    }

}
