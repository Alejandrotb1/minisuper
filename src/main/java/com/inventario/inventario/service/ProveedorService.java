package com.inventario.inventario.service;

import com.inventario.inventario.model.Proveedor;
import com.inventario.inventario.repository.ProveedorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Método para validar y guardar el proveedor
    public String guardarProveedor(Proveedor proveedor, RedirectAttributes redirectAttributes) {
        if (proveedorRepository.existsByNombre(proveedor.getNombre())) {
            redirectAttributes.addFlashAttribute("errorNombre", "El proveedor " + proveedor.getNombre() + " ya existe.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor); // Agregar los datos del proveedor
            return "redirect:/proveedores";
        }
        if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorNoNombre", "El nombre es obligatorio.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor); // Agregar los datos del proveedor
            return "redirect:/proveedores";
        }

        // Validar si el email ya existe
        if (proveedorRepository.existsByEmail(proveedor.getEmail())) {
            redirectAttributes.addFlashAttribute("errorEmail", "El email " + proveedor.getEmail() + " ya existe.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor); // Agregar los datos del proveedor
            return "redirect:/proveedores";
        }

        // Validación de correo electrónico
        if (proveedor.getEmail() == null || proveedor.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorNoEmail", "El correo electrónico es obligatorio.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor); // Agregar los datos del proveedor
            return "redirect:/proveedores";
        }
        if (!proveedor.getEmail().contains("@")) {
            redirectAttributes.addFlashAttribute("errorNoArroba", "El correo electrónico debe contener un '@'.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor); // Agregar los datos del proveedor
            return "redirect:/proveedores";
        }


        if (!proveedor.getTelefono().matches("\\+?[0-9]*")) {
            redirectAttributes.addFlashAttribute("errorTelefono", "El número de teléfono es inválido.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor); // Agregar los datos del proveedor
            return "redirect:/proveedores";
        }
        // Guardar el proveedor
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }
}

