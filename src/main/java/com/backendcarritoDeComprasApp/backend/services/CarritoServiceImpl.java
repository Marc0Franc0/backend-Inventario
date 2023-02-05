package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.backendcarritoDeComprasApp.backend.model.Carrito;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.repository.CarritoRepository;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;

@Service
public class CarritoServiceImpl implements CarritoService {
    @Autowired
    CarritoRepository carritoRepository;
    @Autowired
    ProductoRepository productoRepository;

    @Override
    public Collection<Carrito> getAllCarritos() {

        return carritoRepository.findAll();
    }

    @Override
    public boolean compararCarritos(Carrito datosAlmacenados, Carrito datosIngresados) {
        boolean rta = false;
        if (datosAlmacenados.getProductos().equals(datosIngresados.getProductos())
                && datosAlmacenados.getTotal() == datosIngresados.getTotal())

        {
            rta = true;
        } else {
            rta = false;
        }

        return rta;
    }

    @Override
    public Carrito agregarCarrito(Carrito datosIngresados) {
        Carrito datosNuevos = new Carrito();

        datosNuevos.setProductos(datosIngresados.getProductos());
        datosNuevos.setTotal(datosIngresados.getTotal());
        return carritoRepository.save(datosNuevos);
    }

    @Override
    public String editarCarrito(Long id, Carrito datosIngresados) {
        Carrito datosAlmacenados;
        String rta;
        if (carritoRepository.existsById(id) == true) {
            // Si el producto existe se crea un objeto de tipo Producto con los datos del
            // mismo

            datosAlmacenados = carritoRepository.findById(id).get();

            /*
             * Condicional para evaluar que no sean los mismos datos los que se van a
             * cambiar para no
             * realizar un update innecesario
             */

            if (datosAlmacenados.getProductos().equals(datosIngresados.getProductos())
                    && datosAlmacenados.getTotal() == datosIngresados.getTotal()

            ) {
                rta = "Carrito no modificado";

            } else {

                Carrito datosModificados = new Carrito();

                datosModificados.setId(id);
                datosModificados.setProductos(datosIngresados.getProductos());
                datosModificados.setTotal(datosIngresados.getTotal());

                carritoRepository.save(datosModificados);

                rta = "Carrito modificado correctamente";
            }
        } else {
            rta = "No se encontro un carrito anteriormente por lo que no se puede modficar";

        }

        return rta;
    }

    @Override
    public String eliminarCarrito(Long id) {
        String rta = "";
        if (carritoRepository.existsById(id)) {
            rta = "Carrito elminado correctamente";
            carritoRepository.deleteById(id);
        } else {

            rta = "El Carrito a eliminar no existe";
        }
        return rta;
    }

    @Override
    public Collection<Producto> getProductosDelCarrito(Long id) {
        Carrito carrito = carritoRepository.findById(id).orElseThrow();
        // Producto productoVacio = new Producto();

        if (carrito != null) {

            return carrito.getProductos();

        } else {
            return null;
        }
        // return productos;
    }

    @Override
    public String agregarProductoAlCarrito(Long id, Long idproducto) {
        String rta = "";

        if (carritoRepository.existsById(id) && productoRepository.existsById(idproducto)) {

            Carrito carrito = carritoRepository.findById(id).orElseThrow();
            Producto producto = productoRepository.findById(idproducto).orElseThrow();
            Collection<Producto> productosCarrito = carrito.getProductos();

        
            productosCarrito.add(producto);

            carritoRepository.save(carrito);

            rta = "Producto agregado al carrito correctamente";

        } else {

            rta = "No se pudo agregar el producto al carrito";
        }

        return rta;
    }

  
}