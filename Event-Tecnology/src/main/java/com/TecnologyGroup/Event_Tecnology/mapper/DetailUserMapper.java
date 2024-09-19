package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.DetailUser;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DetailUserMapper {

    private final ModelMapper modelMapper;

    public DetailUser convertToEntity(DetailUserRequestDTO detailUserRequestDTO) {
        return modelMapper.map(detailUserRequestDTO, DetailUser.class);
    }

    public DetailUserResponseDTO convertToDTO(DetailUser detailUser) {
        return modelMapper.map(detailUser, DetailUserResponseDTO.class);
    }
}