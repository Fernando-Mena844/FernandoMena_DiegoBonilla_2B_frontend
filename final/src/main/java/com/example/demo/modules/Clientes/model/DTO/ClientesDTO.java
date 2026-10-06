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
    @Pattern(regexp = "^[0-9+() -]$", message = "El teléfono solo puede contener dígitos, +, (), espacios y guiones")
    @Size(min=7, max = 15, message = "El número de teléfono debe de ser de entre 7 y 15 caracteres.")
    private String telefono;
    @NotBlank @Size (max = 100, message = "El email del cliente no puede exceder los 100 caracteres.")
    @Email(message = "El email del cliente no tiene un formato válido.")
    private String email;
    @NotBlank @Size (max = 100, message = "La dirección del cliente no puede exceder los 100 caracteres.")
    private String direccion;
}
