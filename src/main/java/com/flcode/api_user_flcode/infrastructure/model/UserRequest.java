package com.flcode.api_user_flcode.infrastructure.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {

    private Integer idUser;

    @NotBlank(message = "name not blank")
    private String nombre;

    @NotBlank(message = "lastname not blank")
    private String apellido;

    @NotBlank(message = "mail not blank")
    private String email;

    @NotBlank(message = "password not blank")
    private String password;

    private LocalDate fechaNacimiento;

    @NotBlank(message = "phone not blank")
    private String telefono;

    @NotBlank(message = "direction not blank")
    private String direccion;

    @NotBlank(message = "city not blank")
    private String ciudad;

    @NotBlank(message = "pais not blank")
    private String pais;

    @NotBlank(message = "codigopostal not blank")
    private String codigoPostal;

    private Boolean activo;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaCreacion;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime fechaActualizacion;
}
