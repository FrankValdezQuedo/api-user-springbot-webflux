package com.flcode.api_user_flcode.domain.model;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder

public class Role {
    private Long id;
    private String nombre;
    private String descripcion;

}
