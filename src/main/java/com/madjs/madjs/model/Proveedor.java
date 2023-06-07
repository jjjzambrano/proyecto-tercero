package com.madjs.madjs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Proveedor {
    
    @Id
    @Column("proveedor_id")
    private long proveedorId;    

  
    private String nombre;

    
    private String ruc;

   
    private String direccion;

  
    private String telefono;
}
