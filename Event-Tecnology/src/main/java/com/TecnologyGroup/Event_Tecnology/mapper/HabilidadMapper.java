package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Habilidad;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class HabilidadMapper {

    private final ModelMapper modelMapper;

    public Habilidad convertToEntity(HabilidadRequestDTO habilidadRequestDTO){
        return modelMapper.map(habilidadRequestDTO, Habilidad.class);
    }

    public HabilidadResponseDTO convertToDTO(Habilidad habilidad) {
        return modelMapper.map(habilidad, HabilidadResponseDTO.class);
    }

}