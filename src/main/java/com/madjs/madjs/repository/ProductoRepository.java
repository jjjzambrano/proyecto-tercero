package com.madjs.madjs.repository;
import java.util.List;
import com.madjs.madjs.model.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepository extends CrudRepository <Producto, Long>{
    List<Producto> findAll();
}