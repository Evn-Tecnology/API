package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.IncorrectPasswordException;
import com.TecnologyGroup.Event_Tecnology.exception.UserAlreadyExistsException;
import com.TecnologyGroup.Event_Tecnology.exception.UserDeletedException;
import com.TecnologyGroup.Event_Tecnology.exception.UserNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.UserMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.AuthResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.LoginDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserRegistrationDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.UserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.model.enums.EstadoUsuario;
import com.TecnologyGroup.Event_Tecnology.repository.RolRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import com.TecnologyGroup.Event_Tecnology.security.TokenProvider;
import com.TecnologyGroup.Event_Tecnology.security.UserPrincipal;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;

    public AuthResponseDTO login(LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword())
        );

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
        User user = userPrincipal.getUser();

        boolean isAdmin = user.getRol().getNombreRol().equals("ADMIN");

        String token=tokenProvider.createAccessToken(authentication);

        AuthResponseDTO responseDTO = userMapper.toAuthResponseDTO(user, token);

        return responseDTO;
    }

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
        return userMapper.toUserProfileDTO(user);
    }

    @Transactional
    public UserResponseDTO createUser(UserRegistrationDTO userRegistrationDTO) {
        Optional<User> existingUser = userRepository.findAnyUserByEmail(userRegistrationDTO.getEmail());

        if (existingUser.isPresent()) {
            User user = existingUser.get();
            if (user.getEstado() == EstadoUsuario.ELIMINADO) {
                throw new UserDeletedException("El usuario con este correo está eliminado. Puedes reactivar tu cuenta.");
            } else {
                throw new UserAlreadyExistsException("Ya existe un usuario con el correo: " + userRegistrationDTO.getEmail());
            }
        }
        Rol defaultRole = rolRepository.findByNombreRol("UsuarioNv")
                .orElseThrow(() -> new EntityNotFoundException("El rol no fue encontrado"));
        userRegistrationDTO.setPassword(passwordEncoder.encode(userRegistrationDTO.getPassword()));

        User user = userMapper.toUserEntity(userRegistrationDTO);
        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setRol(defaultRole);
        user.setCreateAt(LocalDate.now());
        user.setEstado(EstadoUsuario.ACTIVO);;

        User savedUser = userRepository.save(user);

        return userMapper.toUserProfileDTO(savedUser);
    }

    @Transactional
    public UserResponseDTO updateUser(Integer id, UserRegistrationDTO userRegistrationDTO) {
        User user = findActiveUserById(id);

        user.setFirstName(userRegistrationDTO.getFirstName());
        user.setLastName(userRegistrationDTO.getLastName());
        user.setUserTelefono(userRegistrationDTO.getTelefono());
        user.setUpdateAt(LocalDate.now());

        user = userRepository.save(user);
        return userMapper.toUserProfileDTO(user);
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

        return userMapper.toUserProfileDTO(user);
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
