package com.backendcarritoDeComprasApp.backend.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
     
  private Long id;


  private String nombre;


  private String imagen_url;


  private double cantidad_en_stock;


  private double precio;


  private  String categoria;


  private  String marca;

    
}
