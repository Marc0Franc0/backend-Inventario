package com.backendcarritoDeComprasApp.backend.dto;
import jakarta.validation.constraints.NotBlank;
public class CategoriaDTO {
   @NotBlank
    String nombre;
    @NotBlank
    String producto;
}
