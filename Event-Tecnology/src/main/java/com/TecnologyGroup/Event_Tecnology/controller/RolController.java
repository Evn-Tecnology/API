package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.RolRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolResponseDTO;
import com.TecnologyGroup.Event_Tecnology.service.RolService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@AllArgsConstructor
public class RolController {
    private final RolService rolService;

    @PostMapping
    public ResponseEntity<RolResponseDTO> createUser(@Valid @RequestBody RolRequestDTO RequestDTO) {
        RolResponseDTO createdRol = rolService.createRol(RequestDTO);
        return new ResponseEntity<>(createdRol, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolResponseDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody RolRequestDTO requestDTO) {
        RolResponseDTO rol = rolService.updateRol(id, requestDTO);
        return new ResponseEntity<>(rol, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<RolResponseDTO> deleteUser(@PathVariable Integer id) {
        rolService.deleteRol(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}