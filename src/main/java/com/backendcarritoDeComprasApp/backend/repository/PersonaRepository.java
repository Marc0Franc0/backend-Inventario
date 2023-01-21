package com.backendcarritoDeComprasApp.backend.repository;



import com.backendcarritoDeComprasApp.backend.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public  interface PersonaRepository extends JpaRepository<Persona,Long> {
    
}
