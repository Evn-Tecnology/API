package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.EventRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.enums.CategoriaEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoEvento;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class EventMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Event convertToEntity(EventRequestDTO dto) {
        Event event = new Event();
        event.setEvent_nombre(dto.getEventNombre());
        event.setCategoriaEvento(CategoriaEvento.valueOf(dto.getCategoriaEvento().toUpperCase()));
        event.setEvent_description(dto.getEventDescripcion());
        event.setEventFecha(dto.getEventFecha());
        event.setEventHora(dto.getEventHora());
        event.setEventFechaFin(dto.getEventFechaFin());
        event.setEventLugar(dto.getEventLugar());
        event.setCapacidad(dto.getCapacidad());
        event.setEsPagado(dto.isEsPagado());
        event.setPrecio(dto.getPrecio());
        event.setTipoEvento(TipoEvento.valueOf(dto.getTipoEvento().toUpperCase()));
        return event;
    }


    public EventResponseDTO convertToDTO(Event event) {
        return modelMapper.map(event, EventResponseDTO.class);
    }

    public void updateEntityFromDTO(EventRequestDTO eventRequestDTO, Event event) {
        modelMapper.map(eventRequestDTO, event); // Aplica los cambios del DTO en la entidad existente
    }
}
