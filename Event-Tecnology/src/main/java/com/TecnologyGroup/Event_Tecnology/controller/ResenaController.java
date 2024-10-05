package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.ResenaRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.ResenaResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoResena;
import com.TecnologyGroup.Event_Tecnology.service.ResenaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resenas")
@AllArgsConstructor
public class ResenaController {

    private final ResenaService resenaService;

    // Crear una nueva reseña
    @PostMapping
    public ResponseEntity<ResenaResponseDTO> crearResena(@RequestBody ResenaRequestDTO resenaRequestDTO) {
        ResenaResponseDTO resenaCreada = resenaService.crearResena(resenaRequestDTO);
        return new ResponseEntity<>(resenaCreada, HttpStatus.CREATED);
    }

    // Actualizar una reseña existente
    @PutMapping("/{resenaId}")
    public ResponseEntity<ResenaResponseDTO> actualizarResena(@PathVariable Integer resenaId,
                                                              @RequestBody ResenaRequestDTO resenaRequestDTO) {
        ResenaResponseDTO resenaActualizada = resenaService.actualizarResena(resenaId, resenaRequestDTO);
        return new ResponseEntity<>(resenaActualizada, HttpStatus.OK);
    }

    // Obtener todas las reseñas
    @GetMapping
    public ResponseEntity<List<ResenaResponseDTO>> obtenerTodasLasResenas() {
        List<ResenaResponseDTO> resenas = resenaService.obtenerTodasLasResenas();
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    // Obtener reseñas por evento
    @GetMapping("/evento/{eventId}")
    public ResponseEntity<List<ResenaResponseDTO>> obtenerResenasPorEvento(@PathVariable Integer eventId) {
        List<ResenaResponseDTO> resenas = resenaService.obtenerResenasPorEvento(eventId);
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    // Obtener reseñas por usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<ResenaResponseDTO>> obtenerResenasPorUsuario(@PathVariable Integer userId) {
        List<ResenaResponseDTO> resenas = resenaService.obtenerResenasPorUsuario(userId);
        return new ResponseEntity<>(resenas, HttpStatus.OK);
    }

    // Obtener una reseña por ID
    @GetMapping("/{resenaId}")
    public ResponseEntity<ResenaResponseDTO> obtenerResenaPorId(@PathVariable Integer resenaId) {
        ResenaResponseDTO resena = resenaService.obtenerResenaPorId(resenaId);
        return new ResponseEntity<>(resena, HttpStatus.OK);
    }

    // Eliminar una reseña por ID
    @DeleteMapping("/{resenaId}")
    public ResponseEntity<Void> eliminarResena(@PathVariable Integer resenaId) {
        resenaService.eliminarResena(resenaId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Cambiar el estado de una reseña (Aprobada, Rechazada, Pendiente)
    @PatchMapping("/{resenaId}/estado")
    public ResponseEntity<ResenaResponseDTO> cambiarEstadoResena(@PathVariable Integer resenaId,
                                                                 @RequestParam EstadoResena nuevoEstado) {
        ResenaResponseDTO resenaActualizada = resenaService.cambiarEstadoResena(resenaId, nuevoEstado);
        return new ResponseEntity<>(resenaActualizada, HttpStatus.OK);
    }
}
