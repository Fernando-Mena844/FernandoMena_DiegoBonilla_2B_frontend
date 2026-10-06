package com.example.demo.modules.Eventos.model.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class EventosDTO {
    private Long id_evento;
    @NotNull(message = "El cliente del evento es obligatorio")
    private Long id_cliente;
    @NotNull(message = "El salón del evento es obligatorio")
    private Long id_salon;
    @NotBlank @Size(max = 100, message = "El nombre del evento no puede exceder los 100 caracteres")
    private String nombre_evento;
    //Opcional: si no se envía, el service usa la fecha actual (DEFAULT SYSDATE en la BD)
    private Date fecha_evento;
    @NotNull @Positive(message = "Un evento debe tener al menos 1 asistente")
    private Integer cantidad_personas;
    @NotNull @Min(value = 1, message = "Un evento debe durar al menos 1 hora") @Max(value = 24, message = "Un evento no puede durar más de 24 horas")
    private Integer cantidad_horas;
    //Opcional: si no se envía, el service usa PENDIENTE (DEFAULT de la BD)
    @Pattern(regexp = "PENDIENTE|CONFIRMADO|CANCELADO|FINALIZADO", message = "El estado debe ser PENDIENTE, CONFIRMADO, CANCELADO o FINALIZADO")
    private String estado;
    //Lo calcula el service (precio del salón x horas); en la BD es NUMBER(8,2) >= 0
    @PositiveOrZero @Digits(integer = 6, fraction = 2, message = "El total del pago excede el máximo permitido (999999.99)")
    private Double total_pago;
}
