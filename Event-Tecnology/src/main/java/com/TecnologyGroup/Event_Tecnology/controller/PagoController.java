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

    @PostMapping
    public ResponseEntity<PagoResponseDTO> crearPago(@RequestBody PagoRequestDTO pagoRequestDTO) {
        PagoResponseDTO pagoCreado = pagoService.crearPago(pagoRequestDTO);
        return new ResponseEntity<>(pagoCreado, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PagoResponseDTO>> obtenerTodosLosPagos() {
        List<PagoResponseDTO> pagos = pagoService.obtenerTodosLosPagos();
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/usuario/{userId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorUsuario(@PathVariable Integer userId) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorUsuario(userId);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/evento/{eventId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorEvento(@PathVariable Integer eventId) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorEvento(eventId);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/inscripcion/{inscripcionId}")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorInscripcion(@PathVariable Integer inscripcionId) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorInscripcion(inscripcionId);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/{pagoId}")
    public ResponseEntity<PagoResponseDTO> obtenerPagoPorId(@PathVariable Integer pagoId) {
        PagoResponseDTO pago = pagoService.obtenerPagoPorId(pagoId);
        return new ResponseEntity<>(pago, HttpStatus.OK);
    }

    @GetMapping("/fechas")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorFecha(@RequestParam LocalDateTime fechaInicio,
                                                                      @RequestParam LocalDateTime fechaFin) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorFecha(fechaInicio, fechaFin);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @GetMapping("/metodo")
    public ResponseEntity<List<PagoResponseDTO>> obtenerPagosPorMetodoPago(@RequestParam String metodoPago) {
        List<PagoResponseDTO> pagos = pagoService.obtenerPagosPorMetodoPago(metodoPago);
        return new ResponseEntity<>(pagos, HttpStatus.OK);
    }

    @DeleteMapping("/{pagoId}")
    public ResponseEntity<Void> eliminarPago(@PathVariable Integer pagoId) {
        pagoService.eliminarPago(pagoId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
