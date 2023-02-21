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

    @GetMapping("/obtenertodas")
    public ResponseEntity<List<Categoria>> obtenerTodos() {
        List<Categoria> listaCategorias = categoriaService.getAllCategorias();

        return new ResponseEntity<>(listaCategorias, HttpStatus.OK);
    }

    @GetMapping("/obtenercategoria")
    public ResponseEntity<Categoria> obtenerCategoria(@RequestParam String name) {
        Categoria categoria = categoriaService.getCategoria(name);

        return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
    }

    @PostMapping("/agregarnueva")
    public ResponseEntity<String> agregarProducto(@RequestBody Categoria datosIngresados) {
        ResponseEntity<String> returnMethod = null;

        if (categoriaService.existByNombre(datosIngresados.getNombre())) {

            returnMethod = new ResponseEntity<>("Hay una categoría existente con ese nombre",
                    HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (datosIngresados.getNombre().equals("")) {
            returnMethod = new ResponseEntity<>("Nombre de la categoría vacío", HttpStatus.BAD_REQUEST);
        } else {
            categoriaService.agregarCategoria(datosIngresados);
            returnMethod = new ResponseEntity<>("Categoría agregada correctamente", HttpStatus.CREATED);

        }
        return returnMethod;
    }

    @PutMapping("/editarexistente/{id}")
    public ResponseEntity<String> editarProducto(@RequestBody Categoria datosIngresados, @PathVariable Long id) {

        ResponseEntity<String> returnMethod = null;

        String rta = categoriaService.editarCategoria(id, datosIngresados);

        switch (rta) {

            case "Categoría no modificada": {
                return returnMethod = new ResponseEntity<>(rta, HttpStatus.BAD_REQUEST);

            }
            case "Categoría modificada correctamente": {
                return returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);

            }
            case "No se encontro una categoría anteriormente por lo que no se puede modficar": {

                return returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);

            }
        }

        return returnMethod;
    }

    @DeleteMapping("/eliminarcategoria/{id}")
    public ResponseEntity<String> eliminarCategoria(@PathVariable Long id) {

        ResponseEntity<String> returnMethod = null;

        String rta = categoriaService.eliminarCategoria(id);

        switch (rta) {
            case "Categoría elminada correctamente": {
                returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);
                break;
            }
            case "La categoría a eliminar no existe": {

                returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);
                break;
            }
        }

        return returnMethod;
    }

}
