package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.InteresTecnologicoRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.InteresTecnologicoResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.InteresTecnologico;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class InteresTecnologicoMapper {

    private final ModelMapper modelMapper;

    public InteresTecnologico convertToEntity(InteresTecnologicoRequestDTO interesTecnologicoRequestDTO){
        return modelMapper.map(interesTecnologicoRequestDTO, InteresTecnologico.class);
    }

    public InteresTecnologicoResponseDTO convertToDTO(InteresTecnologico interesTecnologico) {
        return modelMapper.map(interesTecnologico, InteresTecnologicoResponseDTO.class);
    }

    public List<InteresTecnologicoResponseDTO> convertToListDTO(List<InteresTecnologico> intereses){
        return intereses.stream()
                .map(this::convertToDTO)
                .toList();
    }

}