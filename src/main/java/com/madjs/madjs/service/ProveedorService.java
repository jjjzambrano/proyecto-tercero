package com.madjs.madjs.service;

import java.util.List;

import com.madjs.madjs.model.Proveedor;
import com.madjs.madjs.repository.ProveedorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProveedorService {
    @Autowired
    ProveedorRepository proveedorRepository;


    //Update
    public Proveedor saveProveedor(Proveedor proveedor)
    {
        return proveedorRepository.save(proveedor);

    }

    //R
    public List<Proveedor> listadoProveedor(){

        return proveedorRepository.findAll();

    }

    public Proveedor findById(long id){
        return proveedorRepository.findById(id).get();
    }

    //delete

    public void deleteProveedor(long proveedor){
        proveedorRepository.deleteById(proveedor);
    }
}
