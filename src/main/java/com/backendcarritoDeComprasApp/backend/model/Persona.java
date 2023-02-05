package com.backendcarritoDeComprasApp.backend.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "personas")
@Data

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;

    private String nombre;

    private String apellido;

    @ManyToOne(optional=false,fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Carrito carrito_id;
  
}
