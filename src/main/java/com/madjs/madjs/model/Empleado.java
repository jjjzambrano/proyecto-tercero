package com.madjs.madjs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Empleado {
    
    @Id
    @Column("empleado_id")
    private long empleadoId;

    private String nombre;
    private String cedula;
    private String cargo;
    private String horario;
    private String direccion;
    private String telefono;
}
