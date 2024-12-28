package com.inventario.inventario.service;

import com.inventario.inventario.model.Proveedor;
import com.inventario.inventario.repository.ProveedorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Service
public class ProveedorService {

    @Autowired
    private ProveedorRepository proveedorRepository;


    public List<Proveedor> obtenerTodosLosProveedores() {
        return proveedorRepository.findAll();
    }

    // Método para validar y guardar el proveedor
    public String guardarProveedor(Proveedor proveedor, RedirectAttributes redirectAttributes) {
        if (proveedorRepository.existsByNombre(proveedor.getNombre())) {
            redirectAttributes.addFlashAttribute("errorNombre", "El proveedor " + proveedor.getNombre() + " ya existe.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor);
            return "redirect:/proveedores";
        }
        if (proveedor.getNombre() == null || proveedor.getNombre().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorNoNombre", "El nombre es obligatorio.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor);
            return "redirect:/proveedores";
        }

        // Validar si el email ya existe
        if (proveedorRepository.existsByEmail(proveedor.getEmail())) {
            redirectAttributes.addFlashAttribute("errorEmail", "El email " + proveedor.getEmail() + " ya existe.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor);
            return "redirect:/proveedores";
        }

        // Validación de correo electrónico
        if (proveedor.getEmail() == null || proveedor.getEmail().isEmpty()) {
            redirectAttributes.addFlashAttribute("errorNoEmail", "El correo electrónico es obligatorio.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor);
            return "redirect:/proveedores";
        }
        if (!proveedor.getEmail().contains("@")) {
            redirectAttributes.addFlashAttribute("errorNoArroba", "El correo electrónico debe contener un '@'.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor);
            return "redirect:/proveedores";
        }

        // Validar teléfono
        if (!proveedor.getTelefono().matches("\\+?[0-9]*")) {
            redirectAttributes.addFlashAttribute("errorTelefono", "El número de teléfono es inválido.");
            redirectAttributes.addFlashAttribute("proveedor", proveedor);
            return "redirect:/proveedores";
        }

        // Guardar el proveedor
        proveedorRepository.save(proveedor);
        return "redirect:/proveedores";
    }



    // Método para eliminar un proveedor
    public String eliminarProveedor(Integer id, RedirectAttributes redirectAttributes) {
        if (!proveedorRepository.existsById(id)) {
            redirectAttributes.addFlashAttribute("errorProveedorNoExistente", "El proveedor no existe.");
            return "redirect:/proveedores";
        }

        proveedorRepository.deleteById(id);
        return "redirect:/proveedores";
    }

    public Proveedor buscarProveedorPorId(Integer id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del proveedor no puede ser nulo.");
        }


        Optional<Proveedor> proveedorOpt = proveedorRepository.findById(id);


        return proveedorOpt.orElseThrow(() ->
                new EntityNotFoundException("No se encontró un proveedor con el ID: " + id)
        );

    }
}
