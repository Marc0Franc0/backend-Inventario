package com.backendcarritoDeComprasApp.backend.repository;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendcarritoDeComprasApp.backend.model.Producto;

@Repository
public  interface ProductoRepository extends CrudRepository<Producto,Long>{
    boolean existsById(Long id);
    Collection <Producto> findAll();
    Producto findByNombre(String nombre);
    boolean existsByNombre(String nombre);
}
