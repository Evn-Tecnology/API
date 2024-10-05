package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.InscripcionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.InscripcionResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Inscripcion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class InscripcionMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Inscripcion convertToEntity(InscripcionRequestDTO inscripcionRequestDTO) {
        return modelMapper.map(inscripcionRequestDTO, Inscripcion.class);
    }

    public InscripcionResponseDTO convertToDTO(Inscripcion inscripcion) {
        return modelMapper.map(inscripcion, InscripcionResponseDTO.class);
    }
}
