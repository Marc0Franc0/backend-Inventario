package com.backendcarritoDeComprasApp.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.model.Persona;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.services.CarritoService;
import com.backendcarritoDeComprasApp.backend.services.ProductoService;


@RestController
@RequestMapping("/productos")
public class ProductoController {

@Autowired
ProductoService productoService;


@GetMapping("/obtenertodos")
public ResponseEntity <Producto> obtenerTodos(){
    List<Producto> listaProductos = productoService.getAllProducts();

return new ResponseEntity(listaProductos,HttpStatus.OK);
}

@PostMapping("/agregarnuevo")

public ResponseEntity<Producto> agregarProducto(@RequestBody Producto productoCargado){
   

productoService.agregarProducto(productoCargado);
    return new ResponseEntity("Producto agregado correctamente",HttpStatus.OK);
}
}
