package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.ResenaNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.ResenaMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.ResenaRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.ResenaResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.entity.Resena;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoResena;
import com.TecnologyGroup.Event_Tecnology.repository.EventRepository;
import com.TecnologyGroup.Event_Tecnology.repository.ResenaRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ResenaService {

    private final ResenaRepository resenaRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final ResenaMapper resenaMapper;

    // Crear una nueva reseña
    @Transactional
    public ResenaResponseDTO crearResena(ResenaRequestDTO resenaRequestDTO) {
        Event event = obtenerEventoPorId(resenaRequestDTO.getEventId());
        User user = obtenerUsuarioPorId(resenaRequestDTO.getUserId());

        Resena resena = resenaMapper.convertToEntity(resenaRequestDTO);
        resena.setEvento(event);
        resena.setUsuario(user);
        resena.setFechaPublicacion(LocalDateTime.now());
        resena.setEstado(EstadoResena.PENDIENTE);  // Estado inicial

        Resena nuevaResena = resenaRepository.save(resena);
        return resenaMapper.convertToDTO(nuevaResena);
    }

    // Actualizar una reseña
    @Transactional
    public ResenaResponseDTO actualizarResena(Integer resenaId, ResenaRequestDTO resenaRequestDTO) {
        Resena resena = obtenerResenaPorIdInterno(resenaId);

        Event event = obtenerEventoPorId(resenaRequestDTO.getEventId());
        User user = obtenerUsuarioPorId(resenaRequestDTO.getUserId());

        resenaMapper.convertToEntity(resenaRequestDTO);
        resena.setEvento(event);
        resena.setUsuario(user);

        Resena resenaActualizada = resenaRepository.save(resena);
        return resenaMapper.convertToDTO(resenaActualizada);
    }

    // Obtener todas las reseñas
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO> obtenerTodasLasResenas() {
        List<Resena> resenas = resenaRepository.findAll();
        return resenas.stream()
                .map(resenaMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener reseñas por evento
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO> obtenerResenasPorEvento(Integer eventId) {
        List<Resena> resenas = resenaRepository.findByEvento_Id(eventId);
        return resenas.stream()
                .map(resenaMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener reseñas por usuario
    @Transactional(readOnly = true)
    public List<ResenaResponseDTO> obtenerResenasPorUsuario(Integer userId) {
        List<Resena> resenas = resenaRepository.findByUsuario_Id(userId);
        return resenas.stream()
                .map(resenaMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    // Obtener una reseña por ID
    @Transactional(readOnly = true)
    public ResenaResponseDTO obtenerResenaPorId(Integer resenaId) {
        Resena resena = obtenerResenaPorIdInterno(resenaId);
        return resenaMapper.convertToDTO(resena);
    }

    // Eliminar una reseña por ID
    @Transactional
    public void eliminarResena(Integer resenaId) {
        Resena resena = obtenerResenaPorIdInterno(resenaId);
        resenaRepository.delete(resena);
    }

    // Cambiar el estado de una reseña (Aprobada, Rechazada, Pendiente)
    @Transactional
    public ResenaResponseDTO cambiarEstadoResena(Integer resenaId, EstadoResena nuevoEstado) {
        Resena resena = obtenerResenaPorIdInterno(resenaId);

        resena.setEstado(nuevoEstado);
        Resena resenaActualizada = resenaRepository.save(resena);
        return resenaMapper.convertToDTO(resenaActualizada);
    }

    // Métodos auxiliares para obtener entidades

    private Resena obtenerResenaPorIdInterno(Integer resenaId) {
        return resenaRepository.findById(resenaId)
                .orElseThrow(() -> new ResenaNotFoundException("Reseña no encontrada con el ID: " + resenaId));
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
