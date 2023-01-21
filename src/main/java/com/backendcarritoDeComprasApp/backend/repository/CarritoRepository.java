package com.backendcarritoDeComprasApp.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendcarritoDeComprasApp.backend.model.Carrito;

@Repository
public  interface CarritoRepository extends JpaRepository<Carrito,Long> {
    
}