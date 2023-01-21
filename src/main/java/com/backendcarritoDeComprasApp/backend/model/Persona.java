package com.backendcarritoDeComprasApp.backend.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "personas")
@Data

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
       private Long id;


   /*  @Getter
    @Setter
    boolean admin;*/

    private String nombre;


    private String apellido;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Carrito carrito;

}
