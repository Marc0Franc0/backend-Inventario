package com.backendcarritoDeComprasApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backendcarritoDeComprasApp.backend.model.Marca;

@Repository
public interface MarcaRepository extends CrudRepository<Marca, Long> {

Marca findByNombre(String nombre);

boolean existsByNombre(String nombre);
}
