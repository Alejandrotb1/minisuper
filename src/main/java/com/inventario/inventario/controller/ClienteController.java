package com.inventario.inventario.controller;

import com.inventario.inventario.model.Cliente;
import com.inventario.inventario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public String listarClientes(Model model) {
        if(model.containsAttribute("cliente")){
            Cliente cliente = (Cliente) model.asMap().get("cliente");

        }
        else{
            model.addAttribute("cliente", new Cliente());
        }
        model.addAttribute("clientes", clienteService.obtenerTodos());
        return "clientes";
    }




    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        clienteService.guardar(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEdicion(@PathVariable Long id, Model model) {
        Optional<Cliente> clienteOpt = clienteService.obtenerPorId(id);
        if (clienteOpt.isPresent()) {
            model.addAttribute("cliente", clienteOpt.get());
            return "clientes/formulario";
        }
        return "redirect:/clientes";
    }


    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Long id) {
        clienteService.eliminar(id);
        return "redirect:/clientes";
    }
}

