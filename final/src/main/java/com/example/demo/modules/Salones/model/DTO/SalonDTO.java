package com.example.demo.modules.Salones.model.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SalonDTO {
    private Long id_salon;
    @NotBlank @Size(max=100, message = "El nombre del salon no puede exceder los 100 caracteres")
    private String nombre_salon;
    @NotNull @Positive
    private Integer capacidad;
    @NotNull @PositiveOrZero
    private Double precio_renta;
    @NotBlank @Size(max=100, message = "La ubicación del salón no puede exceder los 100 caracteres")
    private String ubicacion;
}
