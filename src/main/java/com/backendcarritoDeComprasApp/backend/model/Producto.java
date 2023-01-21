package com.backendcarritoDeComprasApp.backend.model;

import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Data
public class Producto {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // private String caracGenerales;
  private String nombre;

  private String imagen_url;
  
  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
private Categoria categoria;

  private boolean stock;

  private double precio;

}
