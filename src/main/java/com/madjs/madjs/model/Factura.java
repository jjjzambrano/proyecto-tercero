package com.madjs.madjs.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;



import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import lombok.Data;

@Data
public class Factura {
    
    @Id
    @Column("factura_id")
    private long facturaId;

    private Date fecha;

    private String ruc;

    private Double subtotal;

    @MappedCollection(idColumn = "factura_id")
    private Set<DetalleFactura> detalleFactura = new HashSet<>();

    
}
