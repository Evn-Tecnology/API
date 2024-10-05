package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.EventNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.EventMapper;
import com.TecnologyGroup.Event_Tecnology.mapper.NotificacionMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.entity.Inscripcion;
import com.TecnologyGroup.Event_Tecnology.model.entity.Notificacion;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoEvento;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoNotificacion;
import com.TecnologyGroup.Event_Tecnology.repository.EventRepository;
import com.TecnologyGroup.Event_Tecnology.repository.InscripcionRepository;
import com.TecnologyGroup.Event_Tecnology.repository.NotificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
        event.setEstado(EstadoEvento.PLANIFICADO);  // Estado inicial del evento
        Event nuevoEvento = eventRepository.save(event);
        return eventMapper.convertToDTO(nuevoEvento);
    }

    @Transactional
    public EventResponseDTO actualizarEvento(Integer eventId, EventRequestDTO eventRequestDTO) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Evento no encontrado con el ID: " + eventId));

        eventMapper.updateEntityFromDTO(eventRequestDTO, event);
        Event eventoActualizado = eventRepository.save(event);

        List<Inscripcion> inscripciones = inscripcionRepository.findByEvento_Id(eventId);

        for (Inscripcion inscripcion : inscripciones) {
            User usuario = inscripcion.getUsuario();

            String mensaje = "El evento " + event.getEvent_nombre() + " ha sido actualizado. "
                    + "Nueva información: Fecha: " + event.getEventFecha()
                    + ", Hora: " + event.getEventHora()
                    + ", Lugar: " + event.getEventLugar();

            NotificacionRequestDTO notificacionRequestDTO = new NotificacionRequestDTO();
            notificacionRequestDTO.setUserId(usuario.getId());
            notificacionRequestDTO.setEventId(event.getId());
            notificacionRequestDTO.setMensaje(mensaje);
            notificacionRequestDTO.setEstado(EstadoNotificacion.NO_LEIDA);
            notificacionRequestDTO.setTipo(TipoNotificacion.ACTUALIZACION_EVENTO);

            Notificacion notificacion = notificacionMapper.convertToEntity(notificacionRequestDTO);
            notificacion.setFechaCreacion(LocalDateTime.now());
            notificacionRepository.save(notificacion);
        }

        return eventMapper.convertToDTO(eventoActualizado);
    }


    @Transactional(readOnly = true)
    public List<EventResponseDTO> obtenerTodosLosEventos() {
        List<Event> eventos = eventRepository.findAll();
        return eventos.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EventResponseDTO obtenerEventoPorId(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Evento no encontrado con el ID: " + eventId));
        return eventMapper.convertToDTO(event);
    }

    @Transactional
    public void eliminarEvento(Integer eventId) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Evento no encontrado con el ID: " + eventId));
        eventRepository.delete(event);
    }

    @Transactional
    public EventResponseDTO cambiarEstadoEvento(Integer eventId, EstadoEvento nuevoEstado) {
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new EventNotFoundException("Evento no encontrado con el ID: " + eventId));
        event.setEstado(nuevoEstado);
        Event eventoActualizado = eventRepository.save(event);
        return eventMapper.convertToDTO(eventoActualizado);
    }


    @Transactional(readOnly = true)
    public List<EventResponseDTO> buscarEventosPorNombre(String nombre) {
        List<Event> eventos = eventRepository.findByNombreContaining(nombre);
        return eventos.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> buscarEventosPorFecha(LocalDate fechaInicio, LocalDate fechaFin) {
        List<Event> eventos = eventRepository.findByFechaBetween(fechaInicio, fechaFin);
        return eventos.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> obtenerEventosPorOrganizador(Integer organizadorId) {
        List<Event> eventos = eventRepository.findByOrganizadorPrincipal_Id(organizadorId);
        return eventos.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> obtenerEventosPorEstado(EstadoEvento estado) {
        List<Event> eventos = eventRepository.findByEstado(estado);
        return eventos.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<EventResponseDTO> obtenerEventosPagados() {
        List<Event> eventos = eventRepository.findByEsPagadoTrue();
        return eventos.stream()
                .map(eventMapper::convertToDTO)
                .collect(Collectors.toList());
    }
}
