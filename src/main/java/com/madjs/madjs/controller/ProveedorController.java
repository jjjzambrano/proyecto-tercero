package com.madjs.madjs.controller;

import com.madjs.madjs.model.Proveedor;
import com.madjs.madjs.service.ProveedorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/proveedor")
public class ProveedorController {
    @Autowired
    ProveedorService proveedorService;

    //Create
    @GetMapping("/formulario")
    private String formulario(Model model){
        model.addAttribute("proveedor", new Proveedor());
        return "proveedor/formulario";
    }

    @PostMapping("/procesaFormulario")
    private String procesaFormulario(Proveedor proveedor, Model model){

        proveedorService.saveProveedor(proveedor);
        model.addAttribute("listadoProveedores", proveedorService.listadoProveedor());
        return "redirect:lista";
    }



    //Read
    @GetMapping({"","/","lista"})
    public String lista(Model model){
        model.addAttribute("listadoProveedores", proveedorService.listadoProveedor());
        return "proveedor/lista";
    }

    //Update
    @GetMapping("/formulario/{id}")
    private String formulario(@PathVariable long id, Model model){

        model.addAttribute("proveedor", proveedorService.findById(id));
        return "proveedor/formulario";
    }

    //Delete
    @GetMapping("/eliminar/{id}")
    private String eliminar(@PathVariable long id, Model model){

        proveedorService.deleteProveedor(id);
        model.addAttribute("listadoProveedores", proveedorService.listadoProveedor());
        return "redirect:/proveedor/lista";
    }
}
