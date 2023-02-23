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
import com.backendcarritoDeComprasApp.backend.model.Categoria;
import com.backendcarritoDeComprasApp.backend.services.CategoriaService;

@RestController
@CrossOrigin(origins = { "http://localhost:4200", "https://frontend-inventarioapp.netlify.app/" })
@RequestMapping("api/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    private static String mensaje = "Ocurrió un error en el servidor";

    @GetMapping("/obtenertodas")
    public ResponseEntity<?> obtenerTodas() {
        List<Categoria> listaCategorias = categoriaService.getAllCategorias();

        if (listaCategorias.size() != 0) {
            return ResponseEntity.status(HttpStatus.OK).body(listaCategorias);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje+": Lista vacía");
        }

    }

    @GetMapping("/obtenercategoria")
    public ResponseEntity<?> obtenerCategoria(@RequestParam String name) {
        Categoria categoria = categoriaService.getCategoria(name);
        if (!categoria.toString().equals("")) {
            return ResponseEntity.status(HttpStatus.OK).body(categoria);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje);
        }

    }

    @PostMapping("/agregarnueva")
    public ResponseEntity<String> agregarCategoria(@RequestBody Categoria datosIngresados) {

        if (categoriaService.existByNombre(datosIngresados.getNombre())) {
            mensaje = "Hay una categoría existente con ese nombre";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);

        } else if (datosIngresados.getNombre().equals("")) {
            mensaje = "Nombre de la categoría vacío";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mensaje);
        } else {

            categoriaService.agregarCategoria(datosIngresados);
            return ResponseEntity.status(HttpStatus.CREATED).body("Categoría agregada correctamente");
        }

    }

    @PutMapping("/editarexistente/{id}")
    public ResponseEntity<String> editarCategoria(@RequestBody Categoria datosIngresados, @PathVariable Long id) {

        String rta = categoriaService.editarCategoria(id, datosIngresados);

        switch (rta) {

            case "Categoría modificada correctamente": {
                return ResponseEntity.status(HttpStatus.OK).body(rta);
            }
            default: {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ":" + rta);
            }
        }

    }

    @DeleteMapping("/eliminarcategoria/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {

        String rta = categoriaService.eliminarCategoria(id);

        switch (rta) {
            case "Categoría elminada correctamente": {
                return ResponseEntity.status(HttpStatus.OK).body(rta);

            }
            default: {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(mensaje + ": " + rta);
            }

        }

    }

}
