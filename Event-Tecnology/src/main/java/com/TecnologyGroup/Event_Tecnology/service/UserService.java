package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.IncorrectPasswordException;
import com.TecnologyGroup.Event_Tecnology.exception.UserAlreadyExistsException;
import com.TecnologyGroup.Event_Tecnology.exception.UserNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.UserMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUserEmail(userRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException("Ya existe un usuario con el correo: " + userRequestDTO.getEmail());
        }
        User user = userMapper.convertToEntity(userRequestDTO);

        String encryptedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setUserPassword(encryptedPassword);
        user.setCreateAt(LocalDate.now());

        userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + id));

        user.setUserNombre(userRequestDTO.getNombre());
        user.setUserApellido(userRequestDTO.getApellido());
        user.setUserTelefono(userRequestDTO.getTelefono());
        user.setUpdateAt(LocalDate.now());

        user = userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    @Transactional
    public void updatePassword(Integer id, String currentPassword, String newPassword) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + id));

        if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
            throw new IncorrectPasswordException("La contraseña actual es incorrecta");
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);

        user.setUserPassword(encryptedPassword);
        userRepository.save(user);
    }
}
