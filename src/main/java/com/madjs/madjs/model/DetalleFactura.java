package com.madjs.madjs.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;

import lombok.Data;

@Data
public class DetalleFactura {
    @Id
    @Column("detalle_factura_id")
    private long detalleFacturaId;
    @Column("cliente_id")
    private long clienteId;
    @Column("producto_id")
    private long productoId;
    @Column("factura_id")
    private long facturaId;
    @Column("cantidad_producto")
    private Integer cantidadProducto;

}
