package com.backendcarritoDeComprasApp.backend.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.backendcarritoDeComprasApp.backend.model.Marca;
import com.backendcarritoDeComprasApp.backend.services.MarcaService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://frontend-inventarioapp.netlify.app/" })
@RequestMapping("api/marcas")
public class MarcaController {

    @Autowired
    MarcaService marcaService;
    private static String mensaje = "Ocurrió un error en el servidor";

    @GetMapping("/obtenertodas")
    public ResponseEntity<?> obtenerTodas() {
        List<Marca> listaCategorias = marcaService.getAllMarcas();
        if (listaCategorias.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(listaCategorias);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ": Lista vacía");
        }

    }

    @GetMapping("/obtenermarca")
    public ResponseEntity<?> obtenerMarca(@RequestParam String name) {
        Marca marca = marcaService.getMarca(name);
if(!marca.toString().equals("")){
        return  ResponseEntity.status(HttpStatus.OK).body(marca);
    }
    else{

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
    }

    @PostMapping(value = "/agregarnueva")
    public ResponseEntity<String> agregarMarca(@RequestBody Marca datosIngresados) {
      
        if (marcaService.existByNombre(datosIngresados.getNombre())) {

            mensaje="Hay una marca existente con ese nombre";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        } else if (datosIngresados.getNombre().equals("")) {
            mensaje="Nombre de la marca vacío";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);

        } else {
            mensaje="Marca agregada correctamente";
            marcaService.agregarMarca(datosIngresados);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoría agregada correctamente");
        }
  
    }

    @PutMapping("/editarexistente/{id}")
    public ResponseEntity<String> editarMarca(@RequestBody Marca datosIngresados, @PathVariable Long id) {

       

        String rta = marcaService.editarMarca(id, datosIngresados);

        switch (rta) {

         
            case "Marca modificada correctamente": {
                return ResponseEntity.status(HttpStatus.OK).body(rta);

            }
             default: {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ":" + rta);

            }
        }

    }

    @DeleteMapping("/eliminarmarca/{id}")
    public ResponseEntity<String> eliminarMarca(@PathVariable Long id) {


        String rta = marcaService.eliminarMarca(id);

        switch (rta) {
            case "Marca elminada correctamente": {
                return ResponseEntity.status(HttpStatus.OK).body(rta);

            }
            default : {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ": " + rta);

            }

        }

    }

}
