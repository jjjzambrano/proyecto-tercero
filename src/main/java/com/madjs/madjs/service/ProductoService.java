package com.madjs.madjs.service;

import java.util.List;

import com.madjs.madjs.model.Producto;
import com.madjs.madjs.repository.ProductoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService{
    @Autowired
    ProductoRepository productoRepository;

    public Producto saveProducto(Producto producto) {
        return productoRepository.save(producto);
    }
    public List<Producto> listaProductos(){
        return productoRepository.findAll();
    }
    public  Producto findById(long id){
        return productoRepository.findById(id).get();
    }
    public void deleteForId(long producto){
        productoRepository.deleteById(producto);
    }
}