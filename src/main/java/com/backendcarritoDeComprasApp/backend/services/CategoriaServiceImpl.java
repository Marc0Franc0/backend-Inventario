package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendcarritoDeComprasApp.backend.model.Categoria;
import com.backendcarritoDeComprasApp.backend.repository.CategoriaRepository;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;

@Service
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    CategoriaRepository repository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Categoria> getAllCategorias() {

        return (List<Categoria>) repository.findAll();
    }

    @Override
    public Categoria agregarCategoria(Categoria datosIngresados) {
        Categoria datosNuevos = new Categoria();

        datosNuevos.setNombre(datosIngresados.getNombre());

        return repository.save(datosNuevos);
    }

    @Override
    public String editarCategoria(Long id, Categoria datosIngresados) {
        Categoria datosAlmacenados;
        String rta;
        if (repository.existsById(id) == true) {
            // Si el producto existe se crea un objeto de tipo Producto con los datos del
            // mismo

            datosAlmacenados = repository.findById(id).get();

            /*
             * Condicional para evaluar que no sean los mismos datos los que se van a
             * cambiar para no
             * realizar un update innecesario
             */

            if (datosAlmacenados.getNombre().equals(datosIngresados.getNombre())) {
                rta = "Categoría no modificado";

            } else {

                Categoria datosModificados = new Categoria();

                datosModificados.setId(id);
                datosModificados.setNombre(datosIngresados.getNombre());

                repository.save(datosModificados);

                rta = "Categoría modificada correctamente";
            }
        } else {
            rta = "No se encontro una categoría anteriormente por lo que no se puede modficar";

        }

        return rta;
    }
   

    @Override
    public String eliminarCategoria(Long id) {
       String  rta = "";
        if(repository.existsById(id)){
            rta = "Categoría elminada correctamente";
repository.deleteById(id);
        }else{

            rta = "La categoría a eliminar no existe";
        }
        return rta;
    }

    /* @Override
    public String agregarProductoACategoria(String nombrecategoria, Long idproducto) {
        String rta = "";
        Categoria categ = categ = repository.findByNombre(nombrecategoria);;

      
        if (repository.existsById(categ.getId()) && productoRepository.existsById(idproducto)) {

            Categoria categoria = repository.findById(categ.getId()).orElseThrow();
            Producto producto = productoRepository.findById(idproducto).orElseThrow();
            Collection<Producto> productosCarrito = categoria.getProductos();

        
            productosCarrito.add(producto);

            repository.save(categoria);

            rta = "Producto agregado a la categoría correctamente";

        } else {

            rta = "No se pudo agregar el producto a la categoría";
        }

        return rta;
    } */

    @Override
    public Categoria getCategoria(String name) {
    
        return  repository.findByNombre(name);
    }
}
