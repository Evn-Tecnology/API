package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.IncorrectPasswordException;
import com.TecnologyGroup.Event_Tecnology.exception.UserAlreadyExistsException;
import com.TecnologyGroup.Event_Tecnology.exception.UserNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.UserMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.DeletedUser;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.repository.DeletedUserRepository;
import com.TecnologyGroup.Event_Tecnology.repository.RolRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RolRepository rolRepository;
    private final DeletedUserRepository deletedUserRepository;
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.convertToListDTO(users);
    }

    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + id));
        return userMapper.convertToDTO(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        if (userRepository.existsByUserEmail(userRequestDTO.getEmail())) {
            throw new UserAlreadyExistsException("Ya existe un usuario con el correo: " + userRequestDTO.getEmail());
        }
        User user = userMapper.convertToEntity(userRequestDTO);
        Rol defaultRole = rolRepository.findByNombreRol("UsuarioNv")
                .orElseThrow(() -> new EntityNotFoundException("El rol 'UsuarioNv' no fue encontrado"));
        String encryptedPassword = passwordEncoder.encode(userRequestDTO.getPassword());
        user.setUserPassword(encryptedPassword);
        user.setRol(defaultRole);
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
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + id));

        DeletedUser deletedUser = new DeletedUser();
        deletedUser.setUserNombre(user.getUserNombre());
        deletedUser.setUserApellido(user.getUserApellido());
        deletedUser.setUserEmail(user.getUserEmail());
        deletedUser.setUserTelefono(user.getUserTelefono());
        deletedUser.setDeletedAt(LocalDate.now());

        deletedUserRepository.save(deletedUser);

        userRepository.delete(user);
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