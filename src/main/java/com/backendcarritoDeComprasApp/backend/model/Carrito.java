package com.backendcarritoDeComprasApp.backend.model;

import java.util.HashSet;
import java.util.Set;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "carritos")
@Getter
@Setter
public class Carrito {

  @Id

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_carrito")
  private Long id;

  /*
   * Los tipos de cascada elegidos para la relacion de las entidades son para que
   * los datos persistan y se actualicen
   */
  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JsonBackReference
  /*
   * COn la anotacion jointable se indica el nombre de la tabla intermediaria
   * entre ambas entidades.
   * La anotacion join column inidica el nombre de una de sus columnas y a la
   * columna que hace referencia
   */
  @JoinTable(name = "carritos_productos", joinColumns = {
      @JoinColumn(name = "carrito_id", referencedColumnName = "id_carrito"), }, inverseJoinColumns = {
          @JoinColumn(name = "producto_id", referencedColumnName = "id_producto"),
      })

  Set<Producto> productos = new HashSet<>();

  private double total;

}
