package com.backendcarritoDeComprasApp.backend.controller;

import java.util.List;
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
import com.backendcarritoDeComprasApp.backend.model.Categoria;
import com.backendcarritoDeComprasApp.backend.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
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

        categoriaService.agregarCategoria(datosIngresados);
        return new ResponseEntity<>("Categoría agregada correctamente", HttpStatus.OK);
    }

    @PutMapping("/editarexistente/{id}")
    public ResponseEntity<String> editarProducto(@RequestBody Categoria datosIngresados, @PathVariable Long id) {

        ResponseEntity<String> returnMethod = null;

        String rta = categoriaService.editarCategoria(id, datosIngresados);

        switch (rta) {

            case "Categoría no modificado": {
                returnMethod = new ResponseEntity<>(rta, HttpStatus.BAD_REQUEST);
                break;
            }
            case "Categoría modificada correctamente": {
                returnMethod = new ResponseEntity<>(rta, HttpStatus.OK);
                break;
            }
            case "No se encontro una categoría anteriormente por lo que no se puede modficar": {

                returnMethod = new ResponseEntity<>(rta, HttpStatus.NOT_FOUND);
                break;
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

  /*   @PostMapping("/{id}/{idproducto}")
    public ResponseEntity<String> agregarProductoACategoria(@RequestParam String nombrecategoria, @PathVariable Long idproducto) {


        return new ResponseEntity<String>(categoriaService.agregarProductoACategoria(nombrecategoria, idproducto), HttpStatus.OK);

    } */
}
