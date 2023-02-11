package com.backendcarritoDeComprasApp.backend.services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.DTO.ProductoDTO;
import com.backendcarritoDeComprasApp.backend.model.Categoria;
import com.backendcarritoDeComprasApp.backend.model.Marca;
import com.backendcarritoDeComprasApp.backend.model.Producto;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository repository;
    @Autowired
    CategoriaService categoriaService;
    @Autowired
    MarcaService marcaService;

    /*
     * El método del service de producto permite agregar un nuevo producto,
     * recibiendo como parametro los atributos de un producto
     */
    @Override
    public String agregarProducto(ProductoDTO datosIngresados) {

       Categoria categoria_producto = categoriaService.getCategoria(datosIngresados.getCategoria());
       Marca marca_producto = marcaService.getMarca(datosIngresados.getMarca());
        Producto datosNuevos = new Producto();
        datosNuevos.setImagen_url(datosIngresados.getImagen_url());
        datosNuevos.setNombre(datosIngresados.getNombre());
        datosNuevos.setPrecio(datosIngresados.getPrecio());
        datosNuevos.setCantidad_en_stock(datosIngresados.getCantidad_en_stock());
        datosNuevos.setCategoria(categoria_producto);
        datosNuevos.setMarca(marca_producto);
        repository.save(datosNuevos);

        return "Producto agregado correctamente";
    }

    @Override
    public Collection<Producto> getAllProducts() {

        return repository.findAll();
    }

    @Override
    public String editarProducto(Long id, ProductoDTO datosIngresados) {
        Producto datosAlmacenados;
        Categoria categoria_producto = categoriaService.getCategoria(datosIngresados.getCategoria());
        Marca marca_producto = marcaService.getMarca(datosIngresados.getMarca());
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
                datosModificados.setCategoria(categoria_producto);
                datosModificados.setMarca(marca_producto);
                // categoria_producto.addProducto(datosAlmacenados);
                repository.save(datosModificados);

                rta = "Producto modificado correctamente";
            }
        } else {
            rta = "No se encontro un Producto anteriormente por lo que no se puede modficar";

        }

        return rta;

    }

    @Override
    public boolean compararProductos(Producto datosAlmacenados, ProductoDTO datosIngresados) {
        boolean rta = false;
        if (datosAlmacenados.getImagen_url().equals(datosIngresados.getImagen_url())
                && datosAlmacenados.getCantidad_en_stock() == datosIngresados.getCantidad_en_stock()
                && datosAlmacenados.getPrecio() == datosIngresados.getPrecio()
                // && datosAlmacenados.getCategoria().equals(datosIngresados.getCategoria())
                && datosAlmacenados.getNombre().equals(datosIngresados.getNombre())
                && datosAlmacenados.getCategoria().equals(datosIngresados.getCategoria())
                && datosAlmacenados.getMarca().equals(datosIngresados.getMarca()))

        {
            rta = true;
        } else {
            rta = false;
        }

        return rta;
    }

    @Override
    public String eliminarProducto(Long id) {
        String rta = "";
        if (repository.existsById(id)) {
            rta = "Producto elminado correctamente";

            repository.deleteById(id);
        } else {

            rta = "El producto a eliminar no existe";
        }
        return rta;
    }
}
