package com.madjs.madjs.repository;

import java.util.List;

import com.madjs.madjs.model.Proveedor;

import org.springframework.data.repository.CrudRepository;

public interface ProveedorRepository extends CrudRepository <Proveedor, Long> {

    List<Proveedor> findAll();

    
}

