package com.TecnologyGroup.Event_Tecnology.model.dto;

import lombok.Data;

@Data
public class AuthResponseDTO {
    private Integer id;
    private String token;
    private String firstName;
    private String lastName;
    private String role;
}
