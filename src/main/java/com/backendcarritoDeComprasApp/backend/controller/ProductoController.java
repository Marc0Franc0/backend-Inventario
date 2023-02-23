package com.backendcarritoDeComprasApp.backend.controller;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendcarritoDeComprasApp.backend.dto.ProductoDTO;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.services.ProductoService;

@RestController
@CrossOrigin(origins = {"http://localhost:4200","https://frontend-inventarioapp.netlify.app/"})
@RequestMapping("api/productos")
public class ProductoController {

    @Autowired
    ProductoService productoService;
    
 private static String mensaje = "Ocurrió un error en el servidor";
    /*
     * Se obtiene una lista de todos los productos
     */
   
@GetMapping("/obtenertodos")
public ResponseEntity<?> getAllCarritos() {
Collection<Producto> listaProductos = productoService.getAllProducts();
if (listaProductos.size() != 0) {
  return ResponseEntity.status(HttpStatus.OK).body(listaProductos);
} else {
  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje+": Lista vacía");
}
  
}

    @PostMapping("/agregarnuevo")
    public ResponseEntity<String> agregarProducto(@RequestBody ProductoDTO datosIngresados) {
      
   
    
      if(productoService.existByNombre(datosIngresados.getNombre())){
      // ResponseRta responserta = new ResponseRta("ERROR", "Ya existe un producto con ese nombre");
      mensaje = "Hay un producto existente con ese nombre";
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);

      }else if(datosIngresados.getNombre().equals("")){
       // ResponseRta responserta = new ResponseRta("ERROR", "El nombre del producto no contiene un valor");  
       mensaje= "Nombre del producto vacío";
       return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
      }else{

        productoService.agregarProducto(datosIngresados); 
         //ResponseRta responserta = new ResponseRta("OK", "Producto creado correctamente");  
         mensaje = "Producto creado correctamente";
         return ResponseEntity.status(HttpStatus.CREATED).body(mensaje);
  
    }
  }

  
    @PutMapping("/editarexistente/{id}")
    
    public ResponseEntity<String> editarProducto(@RequestBody ProductoDTO productoCargado, @PathVariable Long id) {
 
      String rta=   productoService.editarProducto(id,productoCargado);

      switch(rta){

        case "Producto modificado correctamente" : { 
          return ResponseEntity.status(HttpStatus.OK).body(rta);
         
        }
        default  : {

          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ":" + rta);
      
        }
      }

        
    }
    @DeleteMapping("/eliminarproducto/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {

              String rta=   productoService.eliminarProducto(id);
        
              switch(rta){
                case "Producto elminado correctamente" : {
                  return ResponseEntity.status(HttpStatus.OK).body(rta);
                }
                default  : {
        
                  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ": " + rta);
                }
              }
        
              
            }
}
