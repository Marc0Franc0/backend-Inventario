package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;
import com.backendcarritoDeComprasApp.backend.model.Producto;


public interface ProductoService {
    
    public Producto agregarProducto(Producto datosIngresadors);

    public Collection<Producto> getAllProducts();

    public String editarProducto(Long id,Producto datosIngresadors);

    public String eliminarProducto(Long id);
 
    public boolean compararProductos(Producto datosAlmacenados,Producto datosModificados);
}
