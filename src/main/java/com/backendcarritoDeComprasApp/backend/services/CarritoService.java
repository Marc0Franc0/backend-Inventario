package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;
import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.model.Producto;




public interface CarritoService {

   public Collection<Carrito> getAllCarritos();

    public Carrito agregarCarrito(Carrito datosIngresados);

    public String editarCarrito(Long id, Carrito datosIngresados);

    public String eliminarCarrito(Long id);

    public boolean compararCarritos(Carrito datosAlmacenados,Carrito datosModificados);

   public  Collection<Producto> getProductosDelCarrito(Long id);

   public  String agregarProductoAlCarrito(Long id, Long idproducto);
}
