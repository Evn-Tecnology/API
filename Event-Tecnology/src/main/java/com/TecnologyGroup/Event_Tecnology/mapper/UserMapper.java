package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.AuthResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.LoginDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRegistrationDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class UserMapper {

    private final ModelMapper modelMapper;
    private final DetailUserMapper detailUserMapper;

    public User toUserEntity(UserRegistrationDTO registrationDTO) {
        return modelMapper.map(registrationDTO, User.class);
    }

    public UserResponseDTO toUserProfileDTO(User user) {
        UserResponseDTO userProfileDTO = modelMapper.map(user, UserResponseDTO.class);

        if (user.getUserDetail() != null) {
            userProfileDTO.setDetailUser(detailUserMapper.convertToDTO(user.getUserDetail()));
        }

        userProfileDTO.setEstado(user.getEstado().toString());

        return userProfileDTO;
    }

    public User toUserEntity(LoginDTO loginDTO) {
        return modelMapper.map(loginDTO, User.class);
    }

    public AuthResponseDTO toAuthResponseDTO(User user, String token) {
        AuthResponseDTO authResponseDTO = new AuthResponseDTO();
        authResponseDTO.setId(user.getId());
        authResponseDTO.setToken(token);
        authResponseDTO.setFirstName(user.getFirstName());
        authResponseDTO.setLastName(user.getLastName());
        authResponseDTO.setRole(user.getRol().getNombreRol());
        return authResponseDTO;
    }

    public List<UserResponseDTO> convertToListDTO(List<User> users) {
        return users.stream()
                .map(this::toUserProfileDTO)
                .toList();
    }
}

