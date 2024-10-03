package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Habilidad;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public List<HabilidadResponseDTO> convertToListDTO(List<Habilidad> habilidades){
        return habilidades.stream()
                .map(this::convertToDTO)
                .toList();
    }
}