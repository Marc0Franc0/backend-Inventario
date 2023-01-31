package com.backendcarritoDeComprasApp.backend.repository;

import java.util.Collection;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendcarritoDeComprasApp.backend.model.Carrito;

@Repository
public  interface CarritoRepository extends CrudRepository<Carrito,Long> {
    Collection <Carrito> findAll();


}