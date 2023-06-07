package com.madjs.madjs.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.madjs.madjs.model.Factura;



public interface FacturaRepository extends CrudRepository <Factura , Long>{

    List<Factura> findAll();
}