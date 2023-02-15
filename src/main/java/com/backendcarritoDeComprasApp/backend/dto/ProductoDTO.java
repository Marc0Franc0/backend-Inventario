package com.backendcarritoDeComprasApp.backend.dto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
 
  @NotBlank
  private String nombre;

  @NotBlank
  private String imagen_url;

  @NotBlank
  private double cantidad_en_stock;

  @NotBlank
  private double precio;

  @NotBlank
  private  String categoria;

  @NotBlank
  private  String marca;

    
}
