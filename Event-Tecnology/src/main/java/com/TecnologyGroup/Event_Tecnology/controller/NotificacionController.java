package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoNotificacion;
import com.TecnologyGroup.Event_Tecnology.service.NotificacionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
@AllArgsConstructor
public class NotificacionController {

    private final NotificacionService notificacionService;

    @PostMapping
    public ResponseEntity<NotificacionResponseDTO> crearNotificacion(@RequestBody NotificacionRequestDTO notificacionRequestDTO) {
        NotificacionResponseDTO notificacionCreada = notificacionService.crearNotificacion(notificacionRequestDTO);
        return new ResponseEntity<>(notificacionCreada, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerTodasLasNotificaciones() {
        List<NotificacionResponseDTO> notificaciones = notificacionService.obtenerTodasLasNotificaciones();
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerNotificacionesPorUsuario(@PathVariable Integer userId) {
        List<NotificacionResponseDTO> notificaciones = notificacionService.obtenerNotificacionesPorUsuario(userId);
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @GetMapping("/evento/{eventId}")
    public ResponseEntity<List<NotificacionResponseDTO>> obtenerNotificacionesPorEvento(@PathVariable Integer eventId) {
        List<NotificacionResponseDTO> notificaciones = notificacionService.obtenerNotificacionesPorEvento(eventId);
        return new ResponseEntity<>(notificaciones, HttpStatus.OK);
    }

    @GetMapping("/{notificacionId}")
    public ResponseEntity<NotificacionResponseDTO> obtenerNotificacionPorId(@PathVariable Integer notificacionId) {
        NotificacionResponseDTO notificacion = notificacionService.obtenerNotificacionPorId(notificacionId);
        return new ResponseEntity<>(notificacion, HttpStatus.OK);
    }

    @DeleteMapping("/{notificacionId}")
    public ResponseEntity<Void> eliminarNotificacion(@PathVariable Integer notificacionId) {
        notificacionService.eliminarNotificacion(notificacionId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{notificacionId}/estado")
    public ResponseEntity<NotificacionResponseDTO> cambiarEstadoNotificacion(@PathVariable Integer notificacionId,
                                                                             @RequestParam EstadoNotificacion nuevoEstado) {
        NotificacionResponseDTO notificacionActualizada = notificacionService.cambiarEstadoNotificacion(notificacionId, nuevoEstado);
        return new ResponseEntity<>(notificacionActualizada, HttpStatus.OK);
    }
}
