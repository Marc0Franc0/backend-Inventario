package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;

import com.DTO.ProductoDTO;
import com.backendcarritoDeComprasApp.backend.model.Producto;


public interface ProductoService {
    
    public String agregarProducto(ProductoDTO datosIngresadors );

    public Collection<Producto> getAllProducts();

    public String editarProducto(Long id,ProductoDTO datosIngresadors);

    public String eliminarProducto(Long id);
 
    public boolean compararProductos(Producto datosAlmacenados,ProductoDTO datosModificados);
}
