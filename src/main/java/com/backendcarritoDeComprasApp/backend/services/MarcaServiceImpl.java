package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendcarritoDeComprasApp.backend.model.Marca;
import com.backendcarritoDeComprasApp.backend.repository.MarcaRepository;
import com.backendcarritoDeComprasApp.backend.repository.ProductoRepository;

@Service
public class MarcaServiceImpl implements MarcaService{
    
    @Autowired
    MarcaRepository repository;

    @Autowired
    ProductoRepository productoRepository;

    @Override
    public List<Marca> getAllMarcas() {

        return (List<Marca>) repository.findAll();
    }

    @Override
    public Marca agregarMarca(Marca datosIngresados) {
        Marca datosNuevos = new Marca();

        datosNuevos.setNombre(datosIngresados.getNombre());

        return repository.save(datosNuevos);
    }

    @Override
    public String editarMarca(Long id, Marca datosIngresados) {
        Marca datosAlmacenados;
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
                rta = "Marca no modificada";

            } else {

                Marca datosModificados = new Marca();

                datosModificados.setId(id);
                datosModificados.setNombre(datosIngresados.getNombre());

                repository.save(datosModificados);

                rta = "Marca modificada correctamente";
            }
        } else {
            rta = "No se encontro una marca anteriormente por lo que no se puede modficar";

        }

        return rta;
    }
   

    @Override
    public String eliminarMarca(Long id) {
       String  rta = "";
        if(repository.existsById(id)){
            rta = "Marca elminada correctamente";
repository.deleteById(id);
        }else{

            rta = "La marca a eliminar no existe";
        }
        return rta;
    }

    @Override
    public Marca getMarca(String name) {
    
        return  repository.findByNombre(name);
    }

    @Override
    public boolean existByNombre(String nombre) {
        
        return repository.existsByNombre(nombre);
    }
    
}
