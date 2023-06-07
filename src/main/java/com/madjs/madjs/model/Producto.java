package com.madjs.madjs.model;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class Producto {

    @Id
    @Column("producto_id")
    private long productoId;

    private String codigo;

    private String nombre;

    private String descripcion;

    private Double precio;

    private Integer stock;
}