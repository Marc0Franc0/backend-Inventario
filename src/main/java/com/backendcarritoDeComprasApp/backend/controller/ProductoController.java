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

    /*
     * Se obtiene una lista de todos los productos
     */
    @GetMapping("/")
    public  HttpStatus test() {

      return HttpStatus.OK;
    }
@GetMapping("/obtenertodos")
public ResponseEntity<Collection<Producto>> getAllCarritos() {

  return new ResponseEntity<>(productoService.getAllProducts(), HttpStatus.OK);
}

    /*
     * Permite agregar un nuevo producto
     * Parametros:
     {
     "id": 0,
      "nombre": "Nombre producto",
     "imagen_url": "imagen_url",
      "stock": true,
      "precio": 0
      }
     */
    @PostMapping("/agregarnuevo")
    public ResponseEntity<String> agregarProducto(@RequestBody ProductoDTO datosIngresados) {
      
      ResponseEntity<String> returnMethod= null;
      String rta=   productoService.agregarProducto(datosIngresados);
     returnMethod = new ResponseEntity<>(rta, HttpStatus.CREATED);
        return returnMethod;
    }

    /*
     * Permite modificar un producto existente
     * Parametros: ademas de los siguientes tambien se necesita de una número de ir el cual
     * se pasa por el path al final del mismo
     {
     "id": 0,
      "nombre": "Nombre producto",
     "imagen_url": "imagen_url",
      "stock": true,
      "precio": 0
      }
     */
    @PutMapping("/editarexistente/{id}")
    
    public ResponseEntity<String> editarProducto(@RequestBody ProductoDTO productoCargado, @PathVariable Long id) {
 
ResponseEntity<String> returnMethod= null;

      String rta=   productoService.editarProducto(id,productoCargado);

      switch(rta){

        case "Producto no modificado":{ returnMethod = new ResponseEntity<>(rta,HttpStatus.BAD_REQUEST);break;}
        case "Producto modificado correctamente" : {returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);break;}
        case "No se encontro un Producto anteriormente por lo que no se puede modficar" : {

            returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);
            break;
        }
      }

        return returnMethod;
    }
    @DeleteMapping("/eliminarproducto/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable Long id) {
 
        ResponseEntity<String> returnMethod= null;
        
              String rta=   productoService.eliminarProducto(id);
        
              switch(rta){
                case "Producto elminado correctamente" : {returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);break;}
                case "El producto a eliminar no existe" : {
        
                    returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);
                    break;
                }
              }
        
                return returnMethod;
            }
}
