package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.InteresTecnologicoRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.InteresTecnologicoResponseDTO;
import com.TecnologyGroup.Event_Tecnology.service.InteresTecnologicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/intereses-tecnologicos")
@AllArgsConstructor
public class InteresTecnologicoController {

    private final InteresTecnologicoService interesTecnologicoService;

    @GetMapping
    public ResponseEntity<List<InteresTecnologicoResponseDTO>> getAllInteresesTech() {
        List<InteresTecnologicoResponseDTO> intereses = interesTecnologicoService.getAllInteresesTecnologicos();
        return new ResponseEntity<>(intereses, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InteresTecnologicoResponseDTO> getInteresTechById(@PathVariable Integer id) {
        InteresTecnologicoResponseDTO interes = interesTecnologicoService.getInteresById(id);
        return new ResponseEntity<>(interes, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<InteresTecnologicoResponseDTO> createInteresTech(@Valid @RequestBody InteresTecnologicoRequestDTO requestDTO) {
        InteresTecnologicoResponseDTO createdInteresTech = interesTecnologicoService.createInteresTecnologico(requestDTO);
        return new ResponseEntity<>(createdInteresTech, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InteresTecnologicoResponseDTO> updateInteresTech(
            @PathVariable Integer id, @Valid @RequestBody InteresTecnologicoRequestDTO requestDTO) {
        InteresTecnologicoResponseDTO interesTecnologico = interesTecnologicoService.updateInteresTecnologico(id, requestDTO);
        return new ResponseEntity<>(interesTecnologico, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteInteresTech(@PathVariable Integer id) {
        interesTecnologicoService.deleteInteresTecnologico(id);
        return ResponseEntity.ok("Interés tecnológico eliminado correctamente.");
    }
}
