package com.madjs.madjs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Cliente {
    
@Id
@Column("cliente_id")
private long clienteId;

private String nombre;
private String cedula;
private String direccion;
private String telefono;
private String correo;

}
