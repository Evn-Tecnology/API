package com.TecnologyGroup.Event_Tecnology.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String rol;
    private DetailUserResponseDTO detailUser;
    private boolean emailVerified;
}
