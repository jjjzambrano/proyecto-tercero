-- View: public.v_detalle_factura

-- DROP VIEW public.v_detalle_factura;

CREATE OR REPLACE VIEW public.v_detalle_factura
 AS
 SELECT detalle_factura.detalle_factura_id,
    detalle_factura.cantidad_producto,
    factura.fecha,
    factura.ruc,
    cliente.cliente_id,
    cliente.nombre AS cliente_nombre,
    cliente.cedula,
    cliente.direccion,
    cliente.telefono,
    cliente.correo,
    producto.producto_id,
    producto.nombre AS producto_nombre,
    producto.descripcion,
    producto.precio,
    factura.factura_id,
    detalle_factura.cantidad_producto::integer::numeric * producto.precio AS subtotal
   FROM factura factura
     JOIN detalle_factura detalle_factura ON factura.factura_id = detalle_factura.factura_id
     JOIN cliente cliente ON detalle_factura.cliente_id = cliente.cliente_id
     JOIN producto producto ON detalle_factura.producto_id = producto.producto_id;

ALTER TABLE public.v_detalle_factura
    OWNER TO postgres;

-----
select producto_nombre, producto_id, sum(subtotal)
from v_detalle_factura
group by 1,2