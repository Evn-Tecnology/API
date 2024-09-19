package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.UserRequestDTO;
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

    public User convertToEntity(UserRequestDTO userRequestDTO){
        return modelMapper.map(userRequestDTO, User.class);
    }

    public UserResponseDTO convertToDTO(User user) {
        UserResponseDTO dto = modelMapper.map(user, UserResponseDTO.class);
        if (user.getUserDetail() != null) {
            dto.setDetailUser(detailUserMapper.convertToDTO(user.getUserDetail()));
        }
        return dto;
    }

    public List<UserResponseDTO> convertToListDTO(List<User> users){
        return users.stream()
                .map(this::convertToDTO)
                .toList();
    }
}
