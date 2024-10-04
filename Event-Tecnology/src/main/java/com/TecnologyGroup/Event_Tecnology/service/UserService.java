package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.IncorrectPasswordException;
import com.TecnologyGroup.Event_Tecnology.exception.UserAlreadyExistsException;
import com.TecnologyGroup.Event_Tecnology.exception.UserDeletedException;
import com.TecnologyGroup.Event_Tecnology.exception.UserNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.UserMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoUsuario;
import com.TecnologyGroup.Event_Tecnology.repository.RolRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.convertToListDTO(users);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllActivateUsers() {
        List<User> activatedUsers = userRepository.findAllActiveUsers();
        return userMapper.convertToListDTO(activatedUsers);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllDeletedUsers() {
        List<User> deletedUsers = userRepository.findAllDeletedUsers();
        return userMapper.convertToListDTO(deletedUsers);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Integer id) {
        User user = findActiveUserById(id);
        return userMapper.convertToDTO(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        Optional<User> existingUser = userRepository.findAnyUserByEmail(userRequestDTO.getEmail());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (user.getEstado() == EstadoUsuario.ELIMINADO) {
                throw new UserDeletedException("El usuario con este correo está eliminado. Puedes reactivar tu cuenta.");
            } else {
                throw new UserAlreadyExistsException("Ya existe un usuario con el correo: " + userRequestDTO.getEmail());
            }
        }

        User user = userMapper.convertToEntity(userRequestDTO);
        Rol defaultRole = rolRepository.findByNombreRol("UsuarioNv")
                .orElseThrow(() -> new EntityNotFoundException("El rol 'UsuarioNv' no fue encontrado"));
        String encryptedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setUserPassword(encryptedPassword);
        user.setRol(defaultRole);
        user.setCreateAt(LocalDate.now());
        user.setEstado(EstadoUsuario.ACTIVO);;

        userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    @Transactional
    public UserResponseDTO updateUser(Integer id, UserRequestDTO userRequestDTO) {
        User user = findActiveUserById(id);

        user.setUserNombre(userRequestDTO.getNombre());
        user.setUserApellido(userRequestDTO.getApellido());
        user.setUserTelefono(userRequestDTO.getTelefono());
        user.setUpdateAt(LocalDate.now());

        user = userRepository.save(user);
        return userMapper.convertToDTO(user);
    }

    @Transactional
    public void deleteUser(Integer id) {
        User user = findActiveUserById(id);

        if (user.getEstado() == EstadoUsuario.ELIMINADO) {
            throw new UserDeletedException("El usuario ya ha sido eliminado.");
        }

        user.setEstado(EstadoUsuario.ELIMINADO);
        user.setDeletedAt(LocalDate.now());
        userRepository.save(user);
    }

    @Transactional
    public UserResponseDTO reactivateUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("No se encontró un usuario con el id: " + id));

        if (user.getEstado() != EstadoUsuario.ELIMINADO) {
            throw new UserAlreadyExistsException("El usuario no está eliminado y ya está activo.");
        }

        user.setEstado(EstadoUsuario.ACTIVO);
        user.setDeletedAt(null);
        userRepository.save(user);

        return userMapper.convertToDTO(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsersByRole(String rolNombre) {
        List<User> users = userRepository.findByRol(rolNombre);
        return userMapper.convertToListDTO(users);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsersWithVerifiedEmail(boolean verified) {
        List<User> users = userRepository.findAllWithVerifiedEmail(verified);
        return userMapper.convertToListDTO(users);
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getUsersByRegistrationDateRange(LocalDate startDate, LocalDate endDate) {
        List<User> users = userRepository.findByRegistrationDateBetween(startDate, endDate);
        return userMapper.convertToListDTO(users);
    }

    @Transactional
    public void recoverPassword(Integer id, String currentPassword, String newPassword) {
        User user = findActiveUserById(id);

        if (!passwordEncoder.matches(currentPassword, user.getUserPassword())) {
            throw new IncorrectPasswordException("La contraseña actual es incorrecta");
        }

        String encryptedPassword = passwordEncoder.encode(newPassword);
        user.setUserPassword(encryptedPassword);
        userRepository.save(user);
    }

    private User findActiveUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + id));
        if (user.getEstado() == EstadoUsuario.ACTIVO) {
            throw new UserDeletedException("Usuario encontrado pero actualmente inactivo!");
        }
        return user;
    }
}
