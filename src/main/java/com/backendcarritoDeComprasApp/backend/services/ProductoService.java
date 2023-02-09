package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;
import com.backendcarritoDeComprasApp.backend.model.Producto;


public interface ProductoService {
    
    public String agregarProducto(Producto datosIngresadors, String nombrecategoria, String nombremarca );

    public Collection<Producto> getAllProducts();

    public String editarProducto(Long id,Producto datosIngresadors,String nombrecategoria);

    public String eliminarProducto(Long id);
 
    public boolean compararProductos(Producto datosAlmacenados,Producto datosModificados);
}
