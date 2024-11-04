package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.NotificacionNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.NotificacionMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Notificacion;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.repository.NotificacionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificacionService {

    private final NotificacionRepository notificacionRepository;
    private final NotificacionMapper notificacionMapper;

    @Transactional
    public NotificacionResponseDTO crearNotificacion(NotificacionRequestDTO notificacionRequestDTO) {
        Notificacion notificacion = notificacionMapper.convertToEntity(notificacionRequestDTO);
        Notificacion nuevaNotificacion = notificacionRepository.save(notificacion);
        return notificacionMapper.convertToDTO(nuevaNotificacion);
    }

    @Transactional(readOnly = true)
    public List<NotificacionResponseDTO> obtenerTodasLasNotificaciones() {
        List<Notificacion> notificaciones = notificacionRepository.findAll();
        return notificaciones.stream()
                .map(notificacionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotificacionResponseDTO> obtenerNotificacionesPorUsuario(Integer userId) {
        List<Notificacion> notificaciones = notificacionRepository.findByUsuario_Id(userId);
        return notificaciones.stream()
                .map(notificacionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<NotificacionResponseDTO> obtenerNotificacionesPorEvento(Integer eventId) {
        List<Notificacion> notificaciones = notificacionRepository.findByEvento_Id(eventId);
        return notificaciones.stream()
                .map(notificacionMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public NotificacionResponseDTO obtenerNotificacionPorId(Integer notificacionId) {
        Notificacion notificacion = obtenerNotificacionPorIdInterno(notificacionId);
        return notificacionMapper.convertToDTO(notificacion);
    }

    @Transactional
    public void eliminarNotificacion(Integer notificacionId) {
        Notificacion notificacion = obtenerNotificacionPorIdInterno(notificacionId);
        notificacionRepository.delete(notificacion);
    }

    @Transactional
    public NotificacionResponseDTO cambiarEstadoNotificacion(Integer notificacionId, EstadoNotificacion nuevoEstado) {
        Notificacion notificacion = obtenerNotificacionPorIdInterno(notificacionId);
        notificacion.setEstado(nuevoEstado);
        Notificacion notificacionActualizada = notificacionRepository.save(notificacion);
        return notificacionMapper.convertToDTO(notificacionActualizada);
    }

    private Notificacion obtenerNotificacionPorIdInterno(Integer notificacionId) {
        return notificacionRepository.findById(notificacionId)
                .orElseThrow(() -> new NotificacionNotFoundException("Notificación no encontrada con el ID: " + notificacionId));
    }
}
