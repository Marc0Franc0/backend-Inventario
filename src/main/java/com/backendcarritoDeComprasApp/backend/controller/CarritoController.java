package com.backendcarritoDeComprasApp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.services.CarritoService;

@RestController
@RequestMapping("/carritos")
public class CarritoController {
        
@Autowired
CarritoService carritoService;
@GetMapping("/nombres")


List<String> getnombres(){
    
   return carritoService.addProductos();
}





}
