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

    @GetMapping("/obtenertodas")
    public ResponseEntity<List<Marca>> obtenerTodas() {
        List<Marca> listaCategorias = marcaService.getAllMarcas();

        return new ResponseEntity<>(listaCategorias, HttpStatus.OK);
    }

    @GetMapping("/obtenercategoria")
    public ResponseEntity<Marca> obtenerMarca(@RequestParam String name) {
        Marca categoria = marcaService.getMarca(name);

        return new ResponseEntity<Marca>(categoria, HttpStatus.OK);
    }

    @PostMapping(value = "/agregarnueva", consumes = { "application/json" })
    public ResponseEntity<String> agregarMarca(@RequestBody Marca datosIngresados) {
        ResponseEntity<String> returnMethod = null;

        if (marcaService.existByNombre(datosIngresados.getNombre())) {

            returnMethod = new ResponseEntity<>("Hay una marca existente con ese nombre",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (datosIngresados.getNombre().equals("")) {
            returnMethod = new ResponseEntity<>("Nombre de la marca vacío",
                    HttpStatus.BAD_REQUEST);

        } else {
            marcaService.agregarMarca(datosIngresados);
            returnMethod = new ResponseEntity<>("Marca agregada correctamente", HttpStatus.OK);
        }
        return returnMethod;
    }

    @PutMapping("/editarexistente/{id}")
    public ResponseEntity<String> editarMarca(@RequestBody Marca datosIngresados, @PathVariable Long id) {

        ResponseEntity<String> returnMethod = null;

        String rta = marcaService.editarMarca(id, datosIngresados);

        switch (rta) {

            case "Marca no modificada": {
                return returnMethod = new ResponseEntity<>(rta, HttpStatus.BAD_REQUEST);

            }
            case "Marca modificada correctamente": {
                return returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);

            }
            case "No se encontro una marca anteriormente por lo que no se puede modficar": {

                return returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);

            }
        }

        return returnMethod;
    }

    @DeleteMapping("/eliminarcategoria/{id}")
    public ResponseEntity<String> eliminarMarca(@PathVariable Long id) {

        ResponseEntity<String> returnMethod = null;

        String rta = marcaService.eliminarMarca(id);

        switch (rta) {
            case "Marca elminada correctamente": {
                return returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);

            }
            case "La marca a eliminar no existe": {

                return returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);

            }

        }

        return returnMethod;
    }

}
