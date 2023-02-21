package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import com.backendcarritoDeComprasApp.backend.model.Categoria;

public interface CategoriaService {

    public List<Categoria> getAllCategorias();
    
    public Categoria agregarCategoria(Categoria datosIngresados);

    public String editarCategoria(Long id, Categoria datosIngresados);

    public String eliminarCategoria(Long id);

    public boolean existByNombre(String nombre);

 /*    public  String agregarProductoACategoria(String nombrecategoria, Long idproducto); */

    public Categoria getCategoria(String name);
}
