package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.backendcarritoDeComprasApp.backend.model.Carrito;




public interface CarritoService {
    
    Carrito addCarrito(Carrito carrito);

    List<String> addProductos();
    double  calcularTotal();
}
