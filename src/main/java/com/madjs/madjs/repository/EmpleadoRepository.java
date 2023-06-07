package com.madjs.madjs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.madjs.madjs.model.Empleado;

public interface EmpleadoRepository extends CrudRepository <Empleado, Long> {
    
    List<Empleado> findAll();
}
