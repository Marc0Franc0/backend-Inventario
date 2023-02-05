package com.backendcarritoDeComprasApp.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.backendcarritoDeComprasApp.backend.model.Categoria;

@Repository
public  interface CategoriaRepository  extends CrudRepository<Categoria,Long>{
   Categoria findByNombre(String name);
}
