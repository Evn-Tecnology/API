package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.*;
import com.TecnologyGroup.Event_Tecnology.mapper.DetailUserMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.DetailUser;
import com.TecnologyGroup.Event_Tecnology.model.entity.Habilidad;
import com.TecnologyGroup.Event_Tecnology.model.entity.InteresTecnologico;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.repository.DetailUserRepository;
import com.TecnologyGroup.Event_Tecnology.repository.HabilidadRepository;
import com.TecnologyGroup.Event_Tecnology.repository.InteresTecnologicoRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DetailUserService {

    private final DetailUserRepository detailUserRepository;
    private final UserRepository userRepository;
    private final HabilidadRepository habilidadRepository;
    private final InteresTecnologicoRepository interesTecnologicoRepository;
    private final DetailUserMapper detailUserMapper;

    @Transactional(readOnly = true)
    public DetailUserResponseDTO getDetailUserByUserId(Integer userId) {
        DetailUser detailUser = detailUserRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException("Detalles no encontrados para el usuario con ID: " + userId));
        return detailUserMapper.convertToDTO(detailUser);
    }

    @Transactional
    public DetailUserResponseDTO createDetailUser(Integer userId, DetailUserRequestDTO detailUserRequestDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + userId));

        if (user.getUserDetail() != null) {
            throw new DetailUserAlreadyExistsException("El usuario ya tiene detalles de usuario asignados.");
        }

        if (!user.getRol().getNombreRol().equals("UsuarioVr")) {
            throw new InvalidUserRoleException("Solo los usuarios verificados pueden crear detalles de usuario.");
        }

        DetailUser detailUser = new DetailUser();

        if (detailUserRequestDTO.getEdad() > 0) {
            detailUser.setEdad(detailUserRequestDTO.getEdad());
        } else {
            throw new InvalidEdadException("La edad ingresada no es válida.");
        }

        if (detailUserRequestDTO.getUrlLinkedIn() != null) {
            detailUser.setUrlLinkedIn(detailUserRequestDTO.getUrlLinkedIn());
        }
        if (detailUserRequestDTO.getGenero() != null) {
            detailUser.setGenero(detailUserRequestDTO.getGenero());
        }
        if (detailUserRequestDTO.getPais() != null) {
            detailUser.setPais(detailUserRequestDTO.getPais());
        }

        if (detailUserRequestDTO.getHabilidades() != null && !detailUserRequestDTO.getHabilidades().isEmpty()) {
            List<Habilidad> habilidades = habilidadRepository.findAllByHabilidadNombreIn(detailUserRequestDTO.getHabilidades());
            detailUser.setHabilidades(habilidades);
        }
        if (detailUserRequestDTO.getInteresesTecnologicos() != null && !detailUserRequestDTO.getInteresesTecnologicos().isEmpty()) {
            List<InteresTecnologico> intereses = interesTecnologicoRepository.findAllByInteresTecNombreIn(detailUserRequestDTO.getInteresesTecnologicos());
            detailUser.setInteresTecnologicos(intereses);
        }

        detailUser.setUser(user);
        user.setUserDetail(detailUser);

        detailUser = detailUserRepository.save(detailUser);
        userRepository.save(user);

        return detailUserMapper.convertToDTO(detailUser);
    }

    @Transactional
    public DetailUserResponseDTO updateDetailUser(Integer userId, DetailUserRequestDTO detailUserRequestDTO) {
        DetailUser detailUser = detailUserRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException("Detalles no encontrados para el usuario con ID: " + userId));

        if (detailUserRequestDTO.getEdad() > 0 && detailUser.getEdad() == 0) {
            detailUser.setEdad(detailUserRequestDTO.getEdad());
        } else if (detailUserRequestDTO.getEdad() != 0) {
            throw new EdadAlreadyAsignedException("No se puede modificar la edad del detalle de usuario.");
        }

        if (detailUserRequestDTO.getUrlLinkedIn() != null) {
            detailUser.setUrlLinkedIn(detailUserRequestDTO.getUrlLinkedIn());
        }
        if (detailUserRequestDTO.getGenero() != null) {
            detailUser.setGenero(detailUserRequestDTO.getGenero());
        }
        if (detailUserRequestDTO.getPais() != null) {
            detailUser.setPais(detailUserRequestDTO.getPais());
        }

        if (detailUserRequestDTO.getHabilidades() != null && !detailUserRequestDTO.getHabilidades().isEmpty()) {
            List<Habilidad> habilidades = habilidadRepository.findAllByHabilidadNombreIn(detailUserRequestDTO.getHabilidades());
            detailUser.setHabilidades(habilidades);
        }
        if (detailUserRequestDTO.getInteresesTecnologicos() != null && !detailUserRequestDTO.getInteresesTecnologicos().isEmpty()) {
            List<InteresTecnologico> intereses = interesTecnologicoRepository.findAllByInteresTecNombreIn(detailUserRequestDTO.getInteresesTecnologicos());
            detailUser.setInteresTecnologicos(intereses);
        }

        detailUser = detailUserRepository.save(detailUser);

        return detailUserMapper.convertToDTO(detailUser);
    }

    @Transactional
    public void deleteDetailUserByUserId(Integer userId) {
        DetailUser detailUser = detailUserRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException("Detalles no encontrados para el usuario con ID: " + userId));
        detailUserRepository.delete(detailUser);
    }
}
