package com.madjs.madjs.controller;

import com.madjs.madjs.model.Cliente;
import com.madjs.madjs.service.ClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    ClienteService clienteService;

    // Crear
    @GetMapping("/formulario")
    private String formulario(Model model) {
        model.addAttribute("cliente", new Cliente());
        return "cliente/formulario";
    }

    @PostMapping("/procesaFormulario")
    private String procesaFormulario(Cliente cliente, Model model) {
        clienteService.saveCliente(cliente);
        model.addAttribute("listadoClientes", clienteService.listadoCliente());
        return "redirect:lista";
    }

    // Leer
    @GetMapping({ "", "/", "lista" })
    public String lista(Model model) {
        model.addAttribute("listadoClientes", clienteService.listadoCliente());
        return "cliente/lista";
    }

    // Actualizar
    @GetMapping("/formulario/{id}")
    private String formulario(@PathVariable long id, Model model) {

        model.addAttribute("cliente", clienteService.findById(id));
        return "cliente/formulario";
    }

    // Eliminar
    @GetMapping("/eliminar/{id}")
    private String eliminar(@PathVariable long id, Model model) {

        clienteService.deleteCliente(id);
        model.addAttribute("listadoCliente", clienteService.listadoCliente());
        return "redirect:/cliente/lista";
    }
}
