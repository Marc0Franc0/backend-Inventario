package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository repository;

    /*
     * El método del service de producto permite agregar un nuevo producto,
     * recibiendo como parametro los atributos de un producto
     */
    @Override
    public Producto agregarProducto(Producto datosIngresados) {
        Producto datosNuevos = new Producto();

        datosNuevos.setImagen_url(datosIngresados.getImagen_url());
        datosNuevos.setNombre(datosIngresados.getNombre());
        datosNuevos.setPrecio(datosIngresados.getPrecio());
        datosNuevos.setCantidad_en_stock(datosIngresados.getCantidad_en_stock());

        return repository.save(datosNuevos);
    }

    @Override
    public Collection<Producto> getAllProducts() {
      
        return   repository.findAll();
    }

    @Override
    public String editarProducto(Long id, Producto datosIngresados) {
        Producto datosAlmacenados;
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

            if (compararProductos(datosAlmacenados, datosIngresados)) {
                rta = "Producto no modificado";

            } else {

                Producto datosModificados = new Producto();

                datosModificados.setId(id);
                datosModificados.setNombre(datosIngresados.getNombre());
               // datosModificados.setCategoria(datosIngresados.getCategoria());
                datosModificados.setImagen_url(datosIngresados.getImagen_url());
                datosModificados.setCantidad_en_stock(datosIngresados.getCantidad_en_stock());
                datosModificados.setPrecio(datosIngresados.getPrecio());

                repository.save(datosModificados);

                rta = "Producto modificado correctamente";
            }
        } else {
            rta = "No se encontro un Producto anteriormente por lo que no se puede modficar";

        }

        return rta;

    }

    @Override
    public boolean compararProductos(Producto datosAlmacenados, Producto datosIngresados) {
boolean rta=false;
        if (datosAlmacenados.getImagen_url().equals(datosIngresados.getImagen_url())
                && datosAlmacenados.getCantidad_en_stock() == datosIngresados.getCantidad_en_stock()
                &&   datosAlmacenados.getPrecio() == datosIngresados.getPrecio() 
               // && datosAlmacenados.getCategoria().equals(datosIngresados.getCategoria())
                && datosAlmacenados.getNombre().equals(datosIngresados.getNombre())
                )

        {
            rta=true;
        }else{
            rta=false;
        }

        return rta;
    }

    @Override
    public String eliminarProducto(Long id) {
       String  rta = "";
        if(repository.existsById(id)){
            rta = "Producto elminado correctamente";
repository.deleteById(id);
        }else{

            rta = "El producto a eliminar no existe";
        }
        return rta;
    }
}
