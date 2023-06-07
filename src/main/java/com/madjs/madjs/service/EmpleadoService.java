package com.madjs.madjs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.madjs.madjs.model.Empleado;
import com.madjs.madjs.repository.EmpleadoRepository;


@Service
public class EmpleadoService {

    @Autowired
    EmpleadoRepository empleadoRepository;

    public Empleado saveEmpleado(Empleado empleado){
        return empleadoRepository.save(empleado);
    }
    
    public List<Empleado> listadoEmpleado(){
        return empleadoRepository.findAll();
    }

    public Empleado findById(long id)
    {
        return empleadoRepository.findById(id).get();
    }

    public void deleteEmpleado(long empleadoId){
        empleadoRepository.deleteById(empleadoId);
    }
}
