package com.backendcarritoDeComprasApp.backend.controller;

import java.util.Collection;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.repository.CarritoRepository;
import com.backendcarritoDeComprasApp.backend.services.CarritoService;

@RestController
@RequestMapping("/carritos")
public class CarritoController {
        
@Autowired
CarritoRepository repository;

@Autowired
CarritoService carritoService;

@GetMapping("/obtenertodos")
public ResponseEntity<Collection<Carrito>> getAllCarritos( ) {

  return new ResponseEntity<>(carritoService.getAllCarritos(), HttpStatus.OK);
}

@GetMapping("/{id}/productos")
public ResponseEntity<Collection<Producto>> getProductosDelCarrito(@PathVariable Long id){

  return new ResponseEntity<>(carritoService.getProductosDelCarrito(id), HttpStatus.OK);
}

  

@PostMapping("/agregarnuevo")

    public ResponseEntity<Carrito> agregarCarrito(@RequestBody Carrito datosIngresados) {



        carritoService.agregarCarrito(datosIngresados);
        return new ResponseEntity("Carrito agregado correctamente", HttpStatus.CREATED);
    }

    @PutMapping("/editarexistente/{id}")
    
    public ResponseEntity<Carrito> editarProducto(@RequestBody Carrito productoCargado, @PathVariable Long id) {
 
ResponseEntity returnMethod= null;

      String rta= carritoService.editarCarrito(id,productoCargado);

      switch(rta){

        case  "Carrito no modificado":{ returnMethod = new ResponseEntity<>(rta,HttpStatus.BAD_REQUEST);break;}
        case "Carrito modificado correctamente" : {returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_MODIFIED);break;}
        case "No se encontro un carrito anteriormente por lo que no se puede modficar" : {

            returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);
            break;
        }
      }

        return returnMethod;


}


@DeleteMapping("/eliminarcarrito/{id}")
public ResponseEntity<Carrito> eliminarCarrito(@PathVariable Long id) {

    ResponseEntity returnMethod= null;
    
          String rta=   carritoService.eliminarCarrito(id);
    
          switch(rta){
            case "Carrito elminado correctamente" : {returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);break;}
            case "El carrito a eliminar no existe" : {
    
                returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);
                break;
            }
          }
    
            return returnMethod;
        }
      }