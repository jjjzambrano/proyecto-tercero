package com.madjs.madjs.model;

import java.sql.Date;

import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Data;

@Data
@Table("v_detalle_factura")
public class DetalleYFactura {

    @Column("detalle_factura_id")
    private long detalleFacturaId;

    private long facturaId;
    private Date fecha;
    private String ruc;
    private Double subtotal;

    @Column("cliente_id")
    private long clienteId;

    @Column("cliente_nombre")
    private String nombreCliente;
    private String cedula;
    private String direccion;
    private String telefono;
    private String correo;

    @Column("producto_id")
    private long productoId;
 
    @Column("producto_nombre")
    private String nombreProdcuto;
    private String descripcion;
    private Double precio;

    private Integer cantidadProducto;
}
