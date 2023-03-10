package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backendcarritoDeComprasApp.backend.model.Marca;

@Service
public interface MarcaService {
    public List<Marca> getAllMarcas();
    
    public Marca agregarMarca(Marca datosIngresados);

    public String editarMarca(Long id, Marca datosIngresados);

    public String eliminarMarca(Long id);

    public boolean existByNombre(String nombre);

 /*    public  String agregarProductoACategoria(String nombrecategoria, Long idproducto); */

    public Marca getMarca(String name);
}
