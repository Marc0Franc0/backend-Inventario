package com.backendcarritoDeComprasApp.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backendcarritoDeComprasApp.backend.model.Persona;

import com.backendcarritoDeComprasApp.backend.repository.PersonaRepository;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    PersonaRepository repository;

    @Override
    public Persona agregarPersona(Persona persona) {
        // TODO Auto-generated method stub
        return repository.save(persona);
    }

    @Override
    public List<Persona> getAllPersonas() {
        // TODO Auto-generated method stub
        return (List<Persona>) repository.findAll();
    }

    @Override
    public String modificarExistente(Persona datosIngresados,Long id) {

        Persona datosAlmacenados;
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

            if (compararDatos(datosAlmacenados,datosIngresados)

            ) {
                rta = "Carrito no modificado";

            } else {

                Persona datosModificados = new Persona();

                datosModificados.setId(id);
                datosModificados.setApellido(datosIngresados.getApellido());
                datosModificados.setNombre(datosIngresados.getNombre());
                datosModificados.setCarrito_id(datosIngresados.getCarrito_id());

                repository.save(datosModificados);

                rta = "Persona modificada correctamente";
            }
        } else {
            rta = "No se encontro una persona anteriormente por lo que no se puede modficar";

        }

        return rta;
    }


    @Override
    public  void eliminarPersona(Long id) {
        // TODO Auto-generated method stub
        repository.deleteById(id);
    
    }

    private boolean compararDatos(Persona datosAlmacenados, Persona  datosIngresados){

        boolean rta= false;
if(datosAlmacenados.equals(datosIngresados)){

    rta=true;
}else{
    rta=false;
}

        return rta;
    }

}
