-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.7.2
-- PostgreSQL version: 9.4
-- Project Site: pgmodeler.com.br
-- Model Author: ---

SET check_function_bodies = false;
-- ddl-end --


-- Database creation must be done outside an multicommand file.
-- These commands were put in this file only for convenience.
-- -- object: new_database | type: DATABASE --
-- -- DROP DATABASE new_database;
-- CREATE DATABASE new_database
-- ;
-- -- ddl-end --
-- 

-- object: public.cliente | type: TABLE --
-- DROP TABLE public.cliente;
CREATE TABLE public.cliente(
	cliente_id serial,
	nombre character varying NOT NULL,
	cedula character varying NOT NULL,
	direccion character varying NOT NULL,
	telefono character varying NOT NULL,
	correo character varying NOT NULL,
	CONSTRAINT cliente_id PRIMARY KEY (cliente_id)

);
-- ddl-end --
-- object: public.producto | type: TABLE --
-- DROP TABLE public.producto;
CREATE TABLE public.producto(
	producto_id serial NOT NULL,
	codigo character varying NOT NULL,
	nombre character varying NOT NULL,
	descripcion character varying NOT NULL,
	precio numeric(12,2) NOT NULL,
	CONSTRAINT producto_id PRIMARY KEY (producto_id)

);
-- ddl-end --
-- object: public.proveedor | type: TABLE --
-- DROP TABLE public.proveedor;
CREATE TABLE public.proveedor(
	proveedor_id serial NOT NULL,
	nombre character varying NOT NULL,
	ruc character varying NOT NULL,
	direccion character varying NOT NULL,
	telefono character varying NOT NULL,
	CONSTRAINT proveedor_id PRIMARY KEY (proveedor_id)

);
-- ddl-end --
-- object: public.empleado | type: TABLE --
-- DROP TABLE public.empleado;
CREATE TABLE public.empleado(
	empleado_id serial NOT NULL,
	nombre character varying NOT NULL,
	cedula character varying NOT NULL,
	cargo character varying NOT NULL,
	horario character varying NOT NULL,
	direccion character varying NOT NULL,
	telefono character varying NOT NULL,
	CONSTRAINT empleado_id PRIMARY KEY (empleado_id)

);
-- ddl-end --
-- object: public.factura | type: TABLE --
-- DROP TABLE public.factura;
CREATE TABLE public.factura(
	factura_id serial NOT NULL,
	cliente_id serial NOT NULL,
	fecha date NOT NULL,
	ruc character varying NOT NULL,
	total numeric(12,2) NOT NULL,
	CONSTRAINT factura_id PRIMARY KEY (factura_id)

);
-- ddl-end --
-- object: public.detalle_factura | type: TABLE --
-- DROP TABLE public.detalle_factura;
CREATE TABLE public.detalle_factura(
	detalle_factura_id serial NOT NULL,
	producto_id serial NOT NULL,
	factura_id serial NOT NULL,
	detalle character varying NOT NULL,
	CONSTRAINT detalle_factura_id PRIMARY KEY (detalle_factura_id)

);
-- ddl-end --
-- object: cliente_id | type: CONSTRAINT --
-- ALTER TABLE public.factura DROP CONSTRAINT cliente_id;
ALTER TABLE public.factura ADD CONSTRAINT cliente_id FOREIGN KEY (cliente_id)
REFERENCES public.cliente (cliente_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


-- object: factura_id | type: CONSTRAINT --
-- ALTER TABLE public.detalle_factura DROP CONSTRAINT factura_id;
ALTER TABLE public.detalle_factura ADD CONSTRAINT factura_id FOREIGN KEY (factura_id)
REFERENCES public.factura (factura_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


-- object: producto_id | type: CONSTRAINT --
-- ALTER TABLE public.detalle_factura DROP CONSTRAINT producto_id;
ALTER TABLE public.detalle_factura ADD CONSTRAINT producto_id FOREIGN KEY (producto_id)
REFERENCES public.producto (producto_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --



