package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import com.backendcarritoDeComprasApp.backend.model.Persona;



public  interface PersonaService {
   public Persona agregarPersona(Persona persona);

public String modificarExistente(Persona persona, Long id);

public void eliminarPersona(Long id);

   public List<Persona> getAllPersonas();
}
 