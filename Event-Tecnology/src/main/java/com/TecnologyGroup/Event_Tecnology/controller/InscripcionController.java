package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.InscripcionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.InscripcionResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoInscripcion;
import com.TecnologyGroup.Event_Tecnology.service.InscripcionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@AllArgsConstructor
public class InscripcionController {

    private final InscripcionService inscripcionService;

    // Crear una nueva inscripción
    @PostMapping
    public ResponseEntity<InscripcionResponseDTO> crearInscripcion(@RequestBody InscripcionRequestDTO inscripcionRequestDTO) {
        InscripcionResponseDTO inscripcionCreada = inscripcionService.crearInscripcion(inscripcionRequestDTO);
        return new ResponseEntity<>(inscripcionCreada, HttpStatus.CREATED);
    }

    // Actualizar una inscripción existente
    @PutMapping("/{inscripcionId}")
    public ResponseEntity<InscripcionResponseDTO> actualizarInscripcion(@PathVariable Integer inscripcionId,
                                                                        @RequestBody InscripcionRequestDTO inscripcionRequestDTO) {
        InscripcionResponseDTO inscripcionActualizada = inscripcionService.actualizarInscripcion(inscripcionId, inscripcionRequestDTO);
        return new ResponseEntity<>(inscripcionActualizada, HttpStatus.OK);
    }

    // Obtener todas las inscripciones
    @GetMapping
    public ResponseEntity<List<InscripcionResponseDTO>> obtenerTodasLasInscripciones() {
        List<InscripcionResponseDTO> inscripciones = inscripcionService.obtenerTodasLasInscripciones();
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    // Obtener una inscripción por ID
    @GetMapping("/{inscripcionId}")
    public ResponseEntity<InscripcionResponseDTO> obtenerInscripcionPorId(@PathVariable Integer inscripcionId) {
        InscripcionResponseDTO inscripcion = inscripcionService.obtenerInscripcionPorId(inscripcionId);
        return new ResponseEntity<>(inscripcion, HttpStatus.OK);
    }

    // Eliminar una inscripción por ID
    @DeleteMapping("/{inscripcionId}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Integer inscripcionId) {
        inscripcionService.eliminarInscripcion(inscripcionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Confirmar asistencia a un evento
    @PatchMapping("/{inscripcionId}/confirmar-asistencia")
    public ResponseEntity<InscripcionResponseDTO> confirmarAsistencia(@PathVariable Integer inscripcionId) {
        InscripcionResponseDTO inscripcionConfirmada = inscripcionService.confirmarAsistencia(inscripcionId);
        return new ResponseEntity<>(inscripcionConfirmada, HttpStatus.OK);
    }

    // Obtener inscripciones por evento
    @GetMapping("/evento/{eventId}")
    public ResponseEntity<List<InscripcionResponseDTO>> obtenerInscripcionesPorEvento(@PathVariable Integer eventId) {
        List<InscripcionResponseDTO> inscripciones = inscripcionService.obtenerInscripcionesPorEvento(eventId);
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    // Obtener inscripciones por usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<InscripcionResponseDTO>> obtenerInscripcionesPorUsuario(@PathVariable Integer userId) {
        List<InscripcionResponseDTO> inscripciones = inscripcionService.obtenerInscripcionesPorUsuario(userId);
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    // Obtener inscripciones por estado
    @GetMapping("/estado")
    public ResponseEntity<List<InscripcionResponseDTO>> obtenerInscripcionesPorEstado(@RequestParam EstadoInscripcion estado) {
        List<InscripcionResponseDTO> inscripciones = inscripcionService.obtenerInscripcionesPorEstado(estado);
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }

    // Obtener asistencias confirmadas por evento
    @GetMapping("/evento/{eventId}/confirmadas")
    public ResponseEntity<List<InscripcionResponseDTO>> obtenerAsistenciasConfirmadasPorEvento(@PathVariable Integer eventId) {
        List<InscripcionResponseDTO> inscripciones = inscripcionService.obtenerAsistenciasConfirmadasPorEvento(eventId);
        return new ResponseEntity<>(inscripciones, HttpStatus.OK);
    }
}
