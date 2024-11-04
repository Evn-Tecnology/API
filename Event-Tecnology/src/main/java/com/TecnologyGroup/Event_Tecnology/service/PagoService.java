package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.PagoNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.PagoMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.PagoRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.PagoResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Event;
import com.TecnologyGroup.Event_Tecnology.model.entity.Inscripcion;
import com.TecnologyGroup.Event_Tecnology.model.entity.Pago;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.repository.EventRepository;
import com.TecnologyGroup.Event_Tecnology.repository.InscripcionRepository;
import com.TecnologyGroup.Event_Tecnology.repository.PagoRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PagoService {

    private final PagoRepository pagoRepository;
    private final EventRepository eventRepository;
    private final UserRepository userRepository;
    private final InscripcionRepository inscripcionRepository;
    private final PagoMapper pagoMapper;

    @Transactional
    public PagoResponseDTO crearPago(PagoRequestDTO pagoRequestDTO) {
        User usuario = obtenerUsuarioPorId(pagoRequestDTO.getUserId());
        Event evento = obtenerEventoPorId(pagoRequestDTO.getEventId());
        Inscripcion inscripcion = obtenerInscripcionPorId(pagoRequestDTO.getInscripcionId());

        Pago pago = pagoMapper.convertToEntity(pagoRequestDTO);
        pago.setUsuario(usuario);
        pago.setEvento(evento);
        pago.setInscripcion(inscripcion);
        pago.setFechaPago(LocalDateTime.now());

        Pago nuevoPago = pagoRepository.save(pago);
        return pagoMapper.convertToDTO(nuevoPago);
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerTodosLosPagos() {
        List<Pago> pagos = pagoRepository.findAll();
        return pagos.stream()
                .map(pagoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerPagosPorUsuario(Integer userId) {
        List<Pago> pagos = pagoRepository.findByUsuario_Id(userId);
        return pagos.stream()
                .map(pagoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerPagosPorEvento(Integer eventId) {
        List<Pago> pagos = pagoRepository.findByEvento_Id(eventId);
        return pagos.stream()
                .map(pagoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerPagosPorInscripcion(Integer inscripcionId) {
        List<Pago> pagos = pagoRepository.findByInscripcion_Id(inscripcionId);
        return pagos.stream()
                .map(pagoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PagoResponseDTO obtenerPagoPorId(Integer pagoId) {
        Pago pago = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new PagoNotFoundException("Pago no encontrado con el ID: " + pagoId));
        return pagoMapper.convertToDTO(pago);
    }

    @Transactional
    public void eliminarPago(Integer pagoId) {
        Pago pago = pagoRepository.findById(pagoId)
                .orElseThrow(() -> new PagoNotFoundException("Pago no encontrado con el ID: " + pagoId));
        pagoRepository.delete(pago);
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerPagosPorFecha(LocalDateTime fechaInicio, LocalDateTime fechaFin) {
        List<Pago> pagos = pagoRepository.findPagosByFechaPagoBetween(fechaInicio, fechaFin);
        return pagos.stream()
                .map(pagoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PagoResponseDTO> obtenerPagosPorMetodoPago(String metodoPago) {
        List<Pago> pagos = pagoRepository.findByMetodoPago(metodoPago);
        return pagos.stream()
                .map(pagoMapper::convertToDTO)
                .collect(Collectors.toList());
    }

    private User obtenerUsuarioPorId(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con el ID: " + userId));
    }

    private Event obtenerEventoPorId(Integer eventId) {
        return eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Evento no encontrado con el ID: " + eventId));
    }

    private Inscripcion obtenerInscripcionPorId(Integer inscripcionId) {
        return inscripcionRepository.findById(inscripcionId)
                .orElseThrow(() -> new RuntimeException("Inscripción no encontrada con el ID: " + inscripcionId));
    }
}
