package com.backendcarritoDeComprasApp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "productos", uniqueConstraints = @UniqueConstraint(columnNames = "nombre"))

public class Producto {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_producto",unique = false)
  private Long id;

  
  @Column(length = 29)
  private String nombre;


  private String imagen_url;


  private double cantidad_en_stock;

  
  private double precio;

  
  private String detalles;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "fk_categoria", nullable = true, updatable = true)
  private  Categoria categoria;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "fk_marca", nullable = true, updatable = true)
  private  Marca marca;

  
}
