package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.PagoRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.PagoResponseDTO;
import com.TecnologyGroup.Event_Tecnology.service.PagoService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/pagos")
@AllArgsConstructor
public class PagoController {

    private final PagoService pagoService;

    // Crear un nuevo pago
    @PostMapping
    public ResponseEntity<PagoResponseDTO> crearPago(@RequestBody PagoRequestDTO pagoRequestDTO) {
        PagoResponseDTO pagoCreado = pagoService.crearPago(pagoRequestDTO);
        return new ResponseEntity<>(pagoCreado, HttpStatus.CREATED);
    }

    // Obtener todos los pagos
    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> obtenerTodosLosPagos() {
        List<PagoResponseDTO> pagos = pagoService.obtenerTodosLosPagos();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Obtener pagos por usuario
    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorUsuario(@PathVariable Integer userId) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorUsuario(userId);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Obtener pagos por evento
    @GetMapping("/evento/{eventId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorEvento(@PathVariable Integer eventId) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorEvento(eventId);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Obtener pagos por inscripción
    @GetMapping("/inscripcion/{inscripcionId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorInscripcion(@PathVariable Integer inscripcionId) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorInscripcion(inscripcionId);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Obtener un pago por ID
    @GetMapping("/{pagoId}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorId(@PathVariable Integer pagoId) {
        PagoResponseDTO pago = pagoService.obtenerPagoPorId(pagoId);
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    // Obtener pagos por rango de fechas
    @GetMapping("/fechas")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorFecha(@RequestParam LocalDateTime fechaInicio,
                                                                      @RequestParam LocalDateTime fechaFin) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorFecha(fechaInicio, fechaFin);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Obtener pagos por método de pago
    @GetMapping("/metodo")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorMetodoPago(@RequestParam String metodoPago) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorMetodoPago(metodoPago);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    // Eliminar un pago por ID
    @DeleteMapping("/{pagoId}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer pagoId) {
        pagoService.eliminarPago(pagoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
