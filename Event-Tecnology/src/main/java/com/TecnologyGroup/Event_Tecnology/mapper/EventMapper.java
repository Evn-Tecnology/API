package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.EventRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    // Convertir de EventRequestDTO a Event (usado para crear o actualizar un evento)
    public Event convertToEntity(EventRequestDTO eventRequestDTO) {
        return modelMapper.map(eventRequestDTO, Event.class);
    }

    // Convertir de Event a EventResponseDTO (usado para devolver datos al cliente)
    public EventResponseDTO convertToDTO(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }

    // Actualizar una entidad existente desde un DTO (usado para actualizaciones)
    public void updateEntityFromDTO(EventRequestDTO eventRequestDTO, Event event) {
        modelMapper.map(eventRequestDTO, event); // Aplica los cambios del DTO en la entidad existente
    }
}
