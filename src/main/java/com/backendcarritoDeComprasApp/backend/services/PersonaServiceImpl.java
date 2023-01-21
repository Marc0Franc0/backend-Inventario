package com.backendcarritoDeComprasApp.backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendcarritoDeComprasApp.backend.model.Persona;
import com.backendcarritoDeComprasApp.backend.repository.PersonaRepository;
 @Service
public class PersonaServiceImpl implements PersonaService{
   
@Autowired
PersonaRepository repository;

    @Override
    public Persona addPersona(Persona persona) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
