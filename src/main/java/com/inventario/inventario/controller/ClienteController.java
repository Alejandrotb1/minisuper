package com.inventario.inventario.controller;

import com.inventario.inventario.model.Cliente;
import com.inventario.inventario.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Optional;

@Controller
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


//    // Endpoint para buscar clientes por ci_nit
//    @GetMapping("/buscarClientePorNit")
//    public Cliente buscarClientePorNit(@RequestParam String ciNit) {
//        return clienteService.buscarClientePorNit(ciNit);  // Llama al servicio para buscar el cliente
//    }

    @ResponseBody  // Indica que la respuesta será el cuerpo directo en formato JSON
    @RequestMapping(value = "/buscarClientePorNit", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> buscarClientePorNit(@RequestParam("ciNit") String ciNit) {
        Cliente cliente = clienteService.buscarClientePorNit(ciNit);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);  // Devuelve el cliente como JSON con código 200
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("error", "Cliente no encontrado con CI/NIT: " + ciNit));
        }
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

