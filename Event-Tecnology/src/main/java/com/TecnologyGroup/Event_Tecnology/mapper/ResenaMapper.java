package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.ResenaRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.ResenaResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Resena;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ResenaMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Resena convertToEntity(ResenaRequestDTO resenaRequestDTO) {
        return modelMapper.map(resenaRequestDTO, Resena.class);
    }

    public ResenaResponseDTO convertToDTO(Resena resena) {
        return modelMapper.map(resena, ResenaResponseDTO.class);
    }
}
