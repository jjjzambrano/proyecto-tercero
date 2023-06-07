package com.madjs.madjs.repository;

import java.util.List;


import com.madjs.madjs.model.DetalleYFactura;

import org.springframework.data.repository.CrudRepository;

public interface DetalleFacturaRepository extends CrudRepository<DetalleYFactura, Long>{


    

    List<DetalleYFactura> findAll();
    List<DetalleYFactura> findByFacturaId(long id);
}
