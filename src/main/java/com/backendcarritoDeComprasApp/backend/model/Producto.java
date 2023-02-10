package com.backendcarritoDeComprasApp.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
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

  @NonNull
  @Column(length = 29)
  private String nombre;

  @NonNull
  private String imagen_url;

  @NonNull
  private double cantidad_en_stock;

  @NonNull
  private double precio;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "fk_categoria", nullable = true, updatable = true)
  private  Categoria categoria;

  @JsonIgnore
  @ManyToOne
  @JoinColumn(name = "fk_marca", nullable = true, updatable = true)
  private  Marca marca;

  
}
