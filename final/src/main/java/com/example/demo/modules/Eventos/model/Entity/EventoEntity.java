package com.example.demo.modules.Eventos.model.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
@Table(name="EVENTOS")
public class EventoEntity {
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
