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
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/obtenertodas")
    public ResponseEntity<Categoria> obtenerTodos() {
        List<Categoria> listaCategorias = categoriaService.getAllCategorias();

        return new ResponseEntity(listaCategorias, HttpStatus.OK);
    }

    @GetMapping("/obtenercategoria")
    public ResponseEntity<Categoria> obtenerCategoria(@RequestParam String name) {
       Categoria categoria = categoriaService.getCategoria(name);

        return new ResponseEntity(categoria, HttpStatus.OK);
    }

    @PostMapping("/agregarnueva")
    public ResponseEntity<Categoria> agregarProducto(@RequestBody Categoria datosIngresados) {

        categoriaService.agregarCategoria(datosIngresados);
        return new ResponseEntity("Categoría agregada correctamente", HttpStatus.OK);
    }

    @PutMapping("/editarexistente/{id}")
    public ResponseEntity<Categoria> editarProducto(@RequestBody Categoria datosIngresados, @PathVariable Long id) {

        ResponseEntity returnMethod = null;

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
    public ResponseEntity<Categoria> eliminarCategoria(@PathVariable Long id) {

        ResponseEntity returnMethod = null;

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

    @PostMapping("/{id}/{idproducto}")
    public ResponseEntity<Categoria> agregarProductoACategoria(@PathVariable Long id, @PathVariable Long idproducto) {

        return new ResponseEntity(categoriaService.agregarProductoACategoria(id, idproducto), HttpStatus.OK);

    }
}
