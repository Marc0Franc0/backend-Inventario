package com.backendcarritoDeComprasApp.backend.services;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.repository.CarritoRepository;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;
@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    CarritoRepository repository;

    @Autowired
    ProductoService productoService;

    @Autowired
    ProductoRepository REP;

    // Metodos de clase
    // -------------------------------------------------------
    @Override
    public List<String> addProductos() {

        List<Producto> productos = REP.findAll();

List<String> nombres= new ArrayList<>();

for(int i = 0;i<productos.size();i++){
 
    nombres.add(productos.get(i).getNombre());
}
 return nombres;
    

       
    }

    // Métodos implementados
    // -------------------------------------------------------

    @Override
    public Carrito addCarrito(Carrito carrito) {
        Carrito nuevocarrito = new Carrito();

     //   nuevocarrito.setProductos(addProductos(carrito.getProductos()));
        nuevocarrito.setTotal_carrito(0);
        return nuevocarrito;
    }




    @Override
    public double calcularTotal() {
        // TODO Auto-generated method stub
        return 0;
    }

}
