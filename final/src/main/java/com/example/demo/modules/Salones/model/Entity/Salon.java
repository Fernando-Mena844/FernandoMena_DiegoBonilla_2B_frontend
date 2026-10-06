package com.example.demo.modules.Salones.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Entity
@Table(name="SALONES")
public class Salon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID_SALON")
    private Long id_salon;
    @Column(name="NOMBRE_SALON")
    private String nombre_salon;
    @Column(name="CAPACIDAD")
    private Integer capacidad;
    @Column(name="PRECIO_RENTA")
    private Double precio_renta;
    @Column(name="UBICACION")
    private String ubicacion;
}
