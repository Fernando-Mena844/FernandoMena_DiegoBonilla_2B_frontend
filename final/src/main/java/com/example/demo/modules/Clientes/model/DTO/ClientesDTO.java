package com.example.demo.modules.Clientes.model.DTO;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ClientesDTO {
    private Long id_cliente;
    @NotBlank @Size (max = 100, message = "El nombre del cliente no puede exceder los 100 caracteres.")
    private String nombre;
    @NotBlank @Size (max = 100, message = "El apellido del cliente no puede exceder los 100 caracteres.")
    private String apellido;
    @NotBlank @Size (max = 15, message = "El teléfono del cliente no puede exceder los 15 caracteres.")
    private String telefono;
    @NotBlank @Size (max = 100, message = "El email del cliente no puede exceder los 100 caracteres.")
    private String email;
    @NotBlank @Size (max = 100, message = "La dirección del cliente no puede exceder los 100 caracteres.")
    private String direccion;
}
