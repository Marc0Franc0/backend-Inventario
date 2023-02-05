package com.backendcarritoDeComprasApp.backend.repository;



import com.backendcarritoDeComprasApp.backend.model.Persona;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public  interface PersonaRepository extends CrudRepository<Persona,Long> {
    
}
