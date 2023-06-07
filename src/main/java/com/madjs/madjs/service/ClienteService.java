package com.madjs.madjs.service;

import java.util.List;

import com.madjs.madjs.model.Cliente;
import com.madjs.madjs.repository.ClienteRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    // Crear
    public Cliente saveCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    // Leer
    public List<Cliente> listadoCliente() {
        return clienteRepository.findAll();
    }

    public Cliente findById(long id){
        return clienteRepository.findById(id).get();
    }

    // Eliminar
    public void deleteCliente(long cliente) {
        clienteRepository.deleteById(cliente);
    }

}
