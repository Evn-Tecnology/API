package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.EventRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.EventResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoEvento;
import com.TecnologyGroup.Event_Tecnology.service.EventService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/eventos")
@AllArgsConstructor
public class EventController {

    private final EventService eventService;

    @PostMapping
    public ResponseEntity<EventResponseDTO> crearEvento(@RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventoCreado = eventService.crearEvento(eventRequestDTO);
        return new ResponseEntity<>(eventoCreado, HttpStatus.CREATED);
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> actualizarEvento(@PathVariable Integer eventId,
                                                             @RequestBody EventRequestDTO eventRequestDTO) {
        EventResponseDTO eventoActualizado = eventService.actualizarEvento(eventId, eventRequestDTO);
        return new ResponseEntity<>(eventoActualizado, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<EventResponseDTO>> obtenerTodosLosEventos() {
        List<EventResponseDTO> eventos = eventService.obtenerTodosLosEventos();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<EventResponseDTO> obtenerEventoPorId(@PathVariable Integer eventId) {
        EventResponseDTO evento = eventService.obtenerEventoPorId(eventId);
        return new ResponseEntity<>(evento, HttpStatus.OK);
    }

    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> eliminarEvento(@PathVariable Integer eventId) {
        eventService.eliminarEvento(eventId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{eventId}/estado")
    public ResponseEntity<EventResponseDTO> cambiarEstadoEvento(@PathVariable Integer eventId,
                                                                @RequestParam EstadoEvento nuevoEstado) {
        EventResponseDTO eventoActualizado = eventService.cambiarEstadoEvento(eventId, nuevoEstado);
        return new ResponseEntity<>(eventoActualizado, HttpStatus.OK);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EventResponseDTO>> buscarEventosPorNombre(@RequestParam String nombre) {
        List<EventResponseDTO> eventos = eventService.buscarEventosPorNombre(nombre);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<EventResponseDTO>> buscarEventosPorFecha(@RequestParam LocalDate fechaInicio,
                                                                        @RequestParam LocalDate fechaFin) {
        List<EventResponseDTO> eventos = eventService.buscarEventosPorFecha(fechaInicio, fechaFin);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/organizador/{organizadorId}")
    public ResponseEntity<List<EventResponseDTO>> obtenerEventosPorOrganizador(@PathVariable Integer organizadorId) {
        List<EventResponseDTO> eventos = eventService.obtenerEventosPorOrganizador(organizadorId);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/estado")
    public ResponseEntity<List<EventResponseDTO>> obtenerEventosPorEstado(@RequestParam EstadoEvento estado) {
        List<EventResponseDTO> eventos = eventService.obtenerEventosPorEstado(estado);
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

    @GetMapping("/pagados")
    public ResponseEntity<List<EventResponseDTO>> obtenerEventosPagados() {
        List<EventResponseDTO> eventos = eventService.obtenerEventosPagados();
        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }
}
