package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadResponseDTO;
import com.TecnologyGroup.Event_Tecnology.service.HabilidadService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habilidades")
@AllArgsConstructor
public class HabilidadController {

    private final HabilidadService habilidadService;

    @GetMapping
    public ResponseEntity<List<HabilidadResponseDTO>> getAllHabilidades() {
        List<HabilidadResponseDTO> habilidades = habilidadService.getAllHabilidades();
        return new ResponseEntity<>(habilidades, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HabilidadResponseDTO> getHabilidadById(@PathVariable Integer id) {
        HabilidadResponseDTO habilidad = habilidadService.getHabiliadadById(id);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<HabilidadResponseDTO> createHabilidad(@RequestBody HabilidadRequestDTO requestDTO) {
        HabilidadResponseDTO createHabilidad = habilidadService.createHabilidad(requestDTO);
        return new ResponseEntity<>(createHabilidad, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<HabilidadResponseDTO> updateHabilidad(
            @PathVariable Integer id, @Valid @RequestBody HabilidadRequestDTO requestDTO) {
        HabilidadResponseDTO habilidad = habilidadService.updateHabilidad(id, requestDTO);
        return new ResponseEntity<>(habilidad, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHabilidad(@PathVariable Integer id) {
        habilidadService.deleteHabilidad(id);
        return ResponseEntity.ok("Habilidad eliminada correctamente.");
    }
}
