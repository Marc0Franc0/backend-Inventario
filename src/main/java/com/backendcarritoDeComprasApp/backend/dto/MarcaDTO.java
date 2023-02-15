package com.backendcarritoDeComprasApp.backend.dto;
import jakarta.validation.constraints.NotBlank;

public class MarcaDTO {
    @NotBlank
    String nombre;
    @NotBlank
    String producto;
}
