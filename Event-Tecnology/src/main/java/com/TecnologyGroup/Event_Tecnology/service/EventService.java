package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.mapper.EventMapper;
import com.TecnologyGroup.Event_Tecnology.mapper.NotificacionMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoEvento;
import com.TecnologyGroup.Event_Tecnology.repository.EventRepository;
import com.TecnologyGroup.Event_Tecnology.repository.InscripcionRepository;
import com.TecnologyGroup.Event_Tecnology.repository.NotificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@AllArgsConstructor
public class EventService {

    private final EventRepository eventRepository;
    private final InscripcionRepository inscripcionRepository;
    private final NotificacionRepository notificacionRepository;
    private final EventMapper eventMapper;
    private final NotificacionMapper notificacionMapper;

    @Transactional
    public EventResponseDTO crearEvento(EventRequestDTO eventRequestDTO) {
        Event event = eventMapper.convertToEntity(eventRequestDTO);
        event.setEstado(EstadoEvento.PLANIFICADO);
        Event nuevoEvento = eventRepository.save(event);
        return eventMapper.convertToDTO(nuevoEvento);

    }
}
