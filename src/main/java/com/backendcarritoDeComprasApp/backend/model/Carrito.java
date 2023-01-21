package com.backendcarritoDeComprasApp.backend.model;


import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "carritos")
@Data
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany( cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Producto> productos;

    // double costo_envio;
    private double total_carrito;

    // Date dia_de_Entrega;

}
