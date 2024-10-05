package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.service.DetailUserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/detail-users")
@AllArgsConstructor
public class DetailUserController {

    private final DetailUserService detailUserService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<DetailUserResponseDTO> getDetailUserByUserId(@PathVariable Integer userId) {
        DetailUserResponseDTO detailUser = detailUserService.getDetailUserByUserId(userId);
        return new ResponseEntity<>(detailUser, HttpStatus.OK);
    }

    @PostMapping("/user/{userId}")
    public ResponseEntity<DetailUserResponseDTO> createDetailUser(
            @PathVariable Integer userId,
            @Valid @RequestBody DetailUserRequestDTO detailUserRequestDTO) {
        DetailUserResponseDTO detailUser = detailUserService.createDetailUser(userId, detailUserRequestDTO);
        return new ResponseEntity<>(detailUser, HttpStatus.CREATED);
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<DetailUserResponseDTO> updateDetailUser(
            @PathVariable Integer userId,
            @Valid @RequestBody DetailUserRequestDTO detailUserRequestDTO) {
        DetailUserResponseDTO detailUser = detailUserService.updateDetailUser(userId, detailUserRequestDTO);
        return new ResponseEntity<>(detailUser, HttpStatus.OK);
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<String> deleteDetailUserByUserId(@PathVariable Integer userId) {
        detailUserService.deleteDetailUserByUserId(userId);
        return ResponseEntity.ok("Detalles del usuario eliminados correctamente.");
    }
}
