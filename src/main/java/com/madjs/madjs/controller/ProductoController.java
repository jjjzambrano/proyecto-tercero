package com.madjs.madjs.controller;

import java.util.List;

import com.madjs.madjs.model.Producto;
import com.madjs.madjs.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/producto")
public class ProductoController{

    @Autowired
    ProductoService productoService;

    @GetMapping("/lista")
    public String lista(Model model){
        List<Producto> listaProducto = productoService.listaProductos();
        model.addAttribute("listaProducto", listaProducto);
        return "producto/lista";
    }

    @GetMapping("/formulario")
    public String formulario(Model model){
        Producto producto = new Producto();
        model.addAttribute("producto", producto);
        return "producto/formulario";
    }

    @PostMapping("/procesar")
    public String procesar(Model model, Producto producto) {
        productoService.saveProducto(producto);
        return "redirect:lista";
    }

    @GetMapping("/formulario/{id}")
    public String  formulario(@PathVariable long id,Model model){
        Producto producto = productoService.findById(id);
        model.addAttribute("producto", producto);
        return "/producto/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable long id, Model model){
        productoService.deleteForId(id);
                model.addAttribute("listaProductos", productoService.listaProductos());
        return "redirect:/producto/lista";
    }
}