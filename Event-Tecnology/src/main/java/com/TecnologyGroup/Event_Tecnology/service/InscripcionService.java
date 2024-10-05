package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.InscripcionNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.InscripcionMapper;
import com.TecnologyGroup.Event_Tecnology.mapper.NotificacionMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.InscripcionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.InscripcionResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.entity.Inscripcion;
import com.TecnologyGroup.Event_Tecnology.model.entity.Notificacion;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.TipoNotificacion;
import com.TecnologyGroup.Event_Tecnology.repository.EventRepository;
import com.TecnologyGroup.Event_Tecnology.repository.InscripcionRepository;
import com.TecnologyGroup.Event_Tecnology.repository.NotificacionRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final InscripcionMapper inscripcionMapper;
    private final NotificacionRepository notificacionRepository;
    private final NotificacionMapper notificacionMapper;

    @Transactional
    public InscripcionResponseDTO crearInscripcion(InscripcionRequestDTO inscripcionRequestDTO) {
        Event event = obtenerEventoPorId(inscripcionRequestDTO.getEventId());
        User user = obtenerUsuarioPorId(inscripcionRequestDTO.getUserId());

        Inscripcion inscripcion = inscripcionMapper.convertToEntity(inscripcionRequestDTO);
        inscripcion.setEvento(event);
        inscripcion.setUsuario(user);
        inscripcion.setFechaInscripcion(LocalDateTime.now());
        inscripcion.setEstado(EstadoInscripcion.PENDIENTE);
        Inscripcion nuevaInscripcion = inscripcionRepository.save(inscripcion);

        NotificacionRequestDTO notificacionRequestDTO = new NotificacionRequestDTO();
        notificacionRequestDTO.setUserId(user.getId());
        notificacionRequestDTO.setEventId(event.getId());
        notificacionRequestDTO.setMensaje("Te has inscrito exitosamente al evento: "
                + event.getEvent_nombre()
                + ". Fecha: " + event.getEventFecha()
                + ", Hora: " + event.getEventHora()
                + ", Lugar: " + event.getEventLugar());
        notificacionRequestDTO.setEstado(EstadoNotificacion.NO_LEIDA);
        notificacionRequestDTO.setTipo(TipoNotificacion.INSCRIPCION);

        Notificacion notificacion = notificacionMapper.convertToEntity(notificacionRequestDTO);
        notificacion.setFechaCreacion(LocalDateTime.now());
        notificacionRepository.save(notificacion);

        return inscripcionMapper.convertToDTO(nuevaInscripcion);
    }

    @Transactional
    public InscripcionResponseDTO actualizarInscripcion(Integer inscripcionId, InscripcionRequestDTO inscripcionRequestDTO) {
        Inscripcion inscripcion = obtenerInscripcionPorIdInterno(inscripcionId);
        Event event = obtenerEventoPorId(inscripcionRequestDTO.getEventId());
        User user = obtenerUsuarioPorId(inscripcionRequestDTO.getUserId());

        inscripcionMapper.convertToEntity(inscripcionRequestDTO);
        inscripcion.setEvento(event);
        inscripcion.setUsuario(user);

        Inscripcion inscripcionActualizada = inscripcionRepository.save(inscripcion);
        return inscripcionMapper.convertToDTO(inscripcionActualizada);
    }

    @Transactional(readOnly = true)
    public List<InscripcionResponseDTO> obtenerTodasLasInscripciones() {
        List<Inscripcion> inscripciones = inscripcionRepository.findAll();
        return inscripciones.stream()
                .map(inscripcionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public InscripcionResponseDTO obtenerInscripcionPorId(Integer inscripcionId) {
        Inscripcion inscripcion = obtenerInscripcionPorIdInterno(inscripcionId);
        return inscripcionMapper.convertToDTO(inscripcion);
    }

    @Transactional
    public void eliminarInscripcion(Integer inscripcionId) {
        Inscripcion inscripcion = obtenerInscripcionPorIdInterno(inscripcionId);
        inscripcionRepository.delete(inscripcion);
    }

    @Transactional
    public InscripcionResponseDTO confirmarAsistencia(Integer inscripcionId) {
        Inscripcion inscripcion = obtenerInscripcionPorIdInterno(inscripcionId);
        inscripcion.setAsistenciaConfirmada(true);
        Inscripcion inscripcionActualizada = inscripcionRepository.save(inscripcion);
        return inscripcionMapper.convertToDTO(inscripcionActualizada);
    }

    @Transactional(readOnly = true)
    public List<InscripcionResponseDTO> obtenerInscripcionesPorEvento(Integer eventId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByEvento_Id(eventId);
        return inscripciones.stream()
                .map(inscripcionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InscripcionResponseDTO> obtenerInscripcionesPorUsuario(Integer userId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByUsuario_Id(userId);
        return inscripciones.stream()
                .map(inscripcionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InscripcionResponseDTO> obtenerInscripcionesPorEstado(EstadoInscripcion estado) {
        List<Inscripcion> inscripciones = inscripcionRepository.findByEstado(estado);
        return inscripciones.stream()
                .map(inscripcionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<InscripcionResponseDTO> obtenerAsistenciasConfirmadasPorEvento(Integer eventId) {
        List<Inscripcion> inscripciones = inscripcionRepository.findAsistenciasConfirmadasPorEvento(eventId);
        return inscripciones.stream()
                .map(inscripcionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    private Inscripcion obtenerInscripcionPorIdInterno(Integer inscripcionId) {
        return inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new InscripcionNotFoundException("Inscripción no encontrada con el ID: " + inscripcionId));
    }

    private Event obtenerEventoPorId(Integer eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con el ID: " + eventId));
    }

    private User obtenerUsuarioPorId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + userId));
    }
}
