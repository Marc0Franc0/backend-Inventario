package com.backendcarritoDeComprasApp.backend.model;

import java.util.List;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "marcas")
public class Marca {
    
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_marca",unique = false)
  private Long id;

  @Column(length = 50)
  @NonNull
  String nombre;

  @NonNull
  //@JsonManagedReference
  @OneToMany(mappedBy  =  "marca" , cascade  = {CascadeType.ALL})
  private List<Producto> productos;
}
