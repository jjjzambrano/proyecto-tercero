package com.madjs.madjs.service;

import java.util.List;

import com.madjs.madjs.model.DetalleYFactura;
import com.madjs.madjs.model.Factura;
import com.madjs.madjs.repository.FacturaDetalleRepository;
import com.madjs.madjs.repository.FacturaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {
    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    FacturaDetalleRepository facturaDetalleRepository;

    public Factura saveFactura(Factura factura){
        return facturaRepository.save(factura);
    }
    
    public List<Factura> listadoFactura(){
        return facturaRepository.findAll();
    }

    public Factura findById(long id)
    {
        return facturaRepository.findById(id).get();
    }

    public void deleteFactura(long facturaId){
        facturaRepository.deleteById(facturaId);
    }

    public List<DetalleYFactura> vistaFacturaDetalle ()
    {
        return facturaDetalleRepository.findAll();
    }

    public List<DetalleYFactura> vistaFindbyId(long id){
        return facturaDetalleRepository.findByFacturaId(id);
    }




}



