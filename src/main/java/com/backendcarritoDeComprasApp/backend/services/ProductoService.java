package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import com.backendcarritoDeComprasApp.backend.model.Producto;

public interface ProductoService {
    
Producto agregarProducto(Producto producto);

List<String> nombresProductos();

List<Producto> getAllProducts();
}
