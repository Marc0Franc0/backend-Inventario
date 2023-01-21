package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository repository;

    @Override
    public Producto agregarProducto(Producto productoCargado) {
        Producto productoNuevo = new Producto();

        productoNuevo.setImagen_url(productoCargado.getImagen_url());
        productoNuevo.setNombre(productoCargado.getNombre());
        productoNuevo.setPrecio(productoCargado.getPrecio());
        productoNuevo.setStock(productoCargado.isStock());
        
        return repository.save(productoNuevo);
    }

    @Override
    public List<String> nombresProductos() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Producto> getAllProducts() {
        // TODO Auto-generated method stub
        return repository.findAll();
    }

}
