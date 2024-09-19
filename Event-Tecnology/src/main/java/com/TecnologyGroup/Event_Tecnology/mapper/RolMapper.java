package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.RolRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class RolMapper {

    private final ModelMapper modelMapper;

    public Rol convertToEntity(RolRequestDTO rolRequestDTO){
        return modelMapper.map(rolRequestDTO, Rol.class);
    }

    public RolResponseDTO convertToDTO(Rol rol) {
        return modelMapper.map(rol, RolResponseDTO.class);
    }

    public List<RolResponseDTO> convertToListDTO(List<Rol> roles){
        return roles.stream()
                .map(this::convertToDTO)
                .toList();
    }

}
