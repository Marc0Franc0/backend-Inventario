package com.backendcarritoDeComprasApp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.backendcarritoDeComprasApp.backend.model.Persona;
import com.backendcarritoDeComprasApp.backend.services.PersonaService;

@RestController
@RequestMapping("/personas")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/obtenertodas")
    public ResponseEntity<Persona> obtenerTodas() {
        List<Persona> listaPersonas = personaService.getAllPersonas();

        return new ResponseEntity(listaPersonas, HttpStatus.OK);
    }

    @PostMapping("/agregarnueva")
   public ResponseEntity<Persona>  agregarPersona(@RequestBody Persona persona) {

        return new ResponseEntity<>(personaService.agregarPersona(persona),HttpStatus.CREATED) ;
    }

    @PutMapping("/modificarexistente/{id}")
    public ResponseEntity<String> modificarPersona(@RequestBody Persona persona,@PathVariable Long id) {

        return new ResponseEntity<>(personaService.modificarExistente(persona,id),HttpStatus.OK); 
    }

    @DeleteMapping("eliminarpersona/{id}")
    ResponseEntity eliminarPersona(@PathVariable Long id) {
        personaService.eliminarPersona(id);
      return new ResponseEntity(HttpStatus.ACCEPTED);  
    }
}
