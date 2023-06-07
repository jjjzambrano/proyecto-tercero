package com.madjs.madjs.repository;

import java.util.List;

import com.madjs.madjs.model.Cliente;

import org.springframework.data.repository.CrudRepository;

public interface ClienteRepository extends CrudRepository <Cliente, Long>{

    List<Cliente> findAll();
    
}
