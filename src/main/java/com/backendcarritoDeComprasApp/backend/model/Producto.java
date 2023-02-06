package com.backendcarritoDeComprasApp.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Entity
@Table(name = "productos", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))
@Data
public class Producto {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto",unique = false)
  private Long id;

  @NonNull
  @Column(length = 29)
  private String nombre;

  @NonNull
  private String imagen_url;

  @NonNull
  private double cantidad_en_stock;

  
  private double precio;

  /*
   * Los tipos de cascada elegidos para la relacion de las entidades son para que
   * los datos persistan y se actualicen
   */
 /*  @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
  @JsonBackReference */

  /*
   * COn la anotacion jointable se indica el nombre de la tabla intermediaria
   * entre ambas entidades.
   * La anotacion join column inidica el nombre de una de sus columnas y a la
   * columna que hace referencia
   */
/*   @JoinTable(name = "carritos_productos", joinColumns = {
      @JoinColumn(name = "producto_id", referencedColumnName = "id_producto") }, inverseJoinColumns = {
          @JoinColumn(name = "carrito_id", referencedColumnName = "id_carrito")
      })
  Set<Carrito> carritos = new HashSet<>(); */
  @JsonBackReference 
  @ManyToOne
  @JoinColumn(name = "fk_categoria", nullable = false, updatable = false)
  private  Categoria categoria;



  
}
