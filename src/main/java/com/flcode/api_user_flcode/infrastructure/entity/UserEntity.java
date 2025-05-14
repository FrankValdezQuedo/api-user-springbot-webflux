package com.flcode.api_user_flcode.infrastructure.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Table("usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @Column("id")
    private Integer id;

    @Column("nombre")
    private String nombre;

    @Column("apellido")
    private String apellido;

    @Column("email")
    private String email;

    @Column("password")
    private String password;

    @Column("fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column("telefono")
    private String telefono;

    @Column("direccion")
    private String direccion;

    @Column("ciudad")
    private String ciudad;

    @Column("pais")
    private String pais;

    @Column("codigo_postal")
    private String codigoPostal;

    @Column("activo")
    private Boolean activo = true;

    @Column("fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column("fecha_actualizacion")
    private LocalDateTime fechaActualizacion;
}
