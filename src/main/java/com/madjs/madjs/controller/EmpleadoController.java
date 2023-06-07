package com.madjs.madjs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.madjs.madjs.model.Empleado;
import org.springframework.ui.Model;
import com.madjs.madjs.service.EmpleadoService;

@Controller
@RequestMapping("/empleado")
public class EmpleadoController {

    @Autowired
    EmpleadoService empleadoService;
    //Crear

    @GetMapping("/formulario")
    private String formulario(Model model){
        model.addAttribute("empleado", new Empleado());
        return "empleado/formulario";
    }

    @PostMapping("/procesaFormulario")
    private String procesaFormulario(Empleado empleado, Model model){

        empleadoService.saveEmpleado(empleado);
        model.addAttribute("listadoEmpleados", empleadoService.listadoEmpleado());
        return "redirect:lista";
    }

    //Leer
    @GetMapping({"","/","lista"})
    public String lista(Model model){
        model.addAttribute("listadoEmpleados", empleadoService.listadoEmpleado());
        return "empleado/lista";
    }

    //Actualizar
    @GetMapping("/formulario/{id}")
    public String formulario(@PathVariable long id, Model model)
    {
        model.addAttribute("empleado", empleadoService.findById(id));
        return "empleado/formulario";
    }
    
   //Eliminar
   @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable long id, Model model)
    {
        empleadoService.deleteEmpleado(id);
        model.addAttribute("listadoEmpleados", empleadoService.listadoEmpleado());
        return "redirect:/empleado/lista";
    }
}
