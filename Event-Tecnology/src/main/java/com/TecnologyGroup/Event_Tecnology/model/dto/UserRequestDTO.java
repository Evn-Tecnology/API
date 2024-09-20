package com.TecnologyGroup.Event_Tecnology.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "El nombre no puede ser vacío")
    @Size(min = 2, message = "El nombre debe tener al menos 2 caracteres")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser vacío")
    @Size(min = 2, message = "El apellido debe tener al menos 2 caracteres")
    private String apellido;

    @NotBlank(message = "El correo electrónico no puede ser vacío")
    @Email(message = "El correo debe ser válido")
    private String email;

    @Pattern(regexp = "^[+]?[0-9]*[\\s\\-()]*[0-9]+$", message = "El número de teléfono puede contener solo dígitos, espacios, guiones, paréntesis y un signo '+' opcional")
    private String telefono;

    @NotBlank(message = "la contraseña no puede ser vacío")
    @Size(min = 8, max = 20, message = "La contraseña debe tener entre 8 y 20 caracteres")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&+=]).*$", message = "La contraseña debe contener al menos un número, una letra minúscula, una letra mayúscula y un carácter especial")
    private String password;
}
