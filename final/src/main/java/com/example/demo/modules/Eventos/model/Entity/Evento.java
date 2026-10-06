package com.example.demo.modules.Eventos.model.Entity;

import com.example.demo.modules.Clientes.model.Entity.Cliente;
import com.example.demo.modules.Salones.model.Entity.Salon;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
@Entity
@Table(name="EVENTOS")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EVENTO")
    private Long id_evento;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", referencedColumnName = "ID_CLIENTE")
    private Cliente id_cliente;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_SALON", referencedColumnName = "ID_SALON")
    private Salon id_salon;
    @Column(name = "NOMBRE_EVENTO")
    String nombre_evento;
    @Column(name = "FECHA_EVENTO")
    private Date fecha_evento;
    @Column(name = "CANTIDAD_PERSONAS")
    private Integer cantidad_personas;
    @Column(name = "CANTIDAD_HORAS")
    private Integer cantidad_horas;
    @Column(name = "ESTADO")
    private String estado;
    @Column(name = "TOTAL_PAGO")
    private Double total_pago;
}
