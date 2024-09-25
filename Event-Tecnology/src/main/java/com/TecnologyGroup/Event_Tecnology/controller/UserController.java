package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.exception.UserAlreadyExistsException;
import com.TecnologyGroup.Event_Tecnology.exception.UserDeletedException;
import com.TecnologyGroup.Event_Tecnology.exception.UserNotFoundException;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.service.UserService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<UserResponseDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/activated")
    public ResponseEntity<List<UserResponseDTO>> getAllActivatedUsers() {
        List<UserResponseDTO> activatedUsers = userService.getAllActivateUsers();
        return new ResponseEntity<>(activatedUsers, HttpStatus.OK);
    }

    @GetMapping("/deleted")
    public ResponseEntity<List<UserResponseDTO>> getAllDeletedUsers() {
        List<UserResponseDTO> deletedUsers = userService.getAllDeletedUsers();
        return new ResponseEntity<>(deletedUsers, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Integer id) {
        UserResponseDTO user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO createdUser = userService.createUser(userRequestDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Integer id, @Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO user = userService.updateUser(id, userRequestDTO);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<UserResponseDTO> restoreUser(@PathVariable Integer id) {
        UserResponseDTO restoredUser = userService.reactivateUser(id);
        return new ResponseEntity<>(restoredUser, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado correctamente.");
        } catch (UserNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado con el ID: " + id);
        } catch (UserDeletedException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El usuario ya está eliminado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error al eliminar el usuario.");
        }
    }

}
