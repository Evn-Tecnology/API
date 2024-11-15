package com.TecnologyGroup.Event_Tecnology.controller;

import com.TecnologyGroup.Event_Tecnology.model.dto.AuthResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.LoginDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRegistrationDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.service.JwtUtilService;
import com.TecnologyGroup.Event_Tecnology.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    @Autowired
    private final UserService userService;

    @Autowired
    private JwtUtilService jwtUtilService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRegistrationDTO userRegistrationDTO) {
        UserResponseDTO createdUser = userService.createUser(userRegistrationDTO);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid  @RequestBody LoginDTO loginDTO) {
        AuthResponseDTO authResponse = userService.login(loginDTO);
        return new ResponseEntity<>(authResponse, HttpStatus.OK);
    }
}
