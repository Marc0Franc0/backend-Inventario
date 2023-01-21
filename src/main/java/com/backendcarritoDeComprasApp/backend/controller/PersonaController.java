package com.backendcarritoDeComprasApp.backend.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.model.Persona;
@RestController
@RequestMapping("/personas")
public class PersonaController {
    @PostMapping("/addpersona")
Persona addCarrito(@RequestBody Carrito carrito){

    Persona nuevaPersona = new Persona();


    nuevaPersona.setNombre("Marco");
    nuevaPersona.setApellido("Franco");
    nuevaPersona.setCarrito(agregarCarrito());

//re.save(nuevaPersona);
return nuevaPersona;

}

Carrito agregarCarrito(){
    
    return null;
    }

}
