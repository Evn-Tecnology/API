package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.exception.UserNotFoundException;
import com.TecnologyGroup.Event_Tecnology.mapper.DetailUserMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.DetailUserResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.DetailUser;
import com.TecnologyGroup.Event_Tecnology.model.entity.User;
import com.TecnologyGroup.Event_Tecnology.repository.DetailUserRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class DetailUserService {

    private final DetailUserRepository detailUserRepository;
    private final UserRepository userRepository;
    private final DetailUserMapper detailUserMapper;

    @Transactional(readOnly = true)
    public DetailUserResponseDTO getDetailUserByUserId(Integer userId) {
        DetailUser detailUser = detailUserRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException("Detalles no encontrados para el usuario con ID: " + userId));
        return detailUserMapper.convertToDTO(detailUser);
    }

    @Transactional
    public DetailUserResponseDTO createOrUpdateDetailUser(Integer userId, DetailUserRequestDTO detailUserRequestDTO) {
        DetailUser detailUserRequest = detailUserMapper.convertToEntity(detailUserRequestDTO);

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("Usuario no encontrado con el identificador: " + userId));

        DetailUser detailUser = detailUserRepository.findByUser_Id(userId).orElse(null);

        if (detailUser == null) {
            detailUser = new DetailUser();
            detailUser.setUser(user);
            user.setUserDetail(detailUser);
        }

        if (detailUserRequest.getUrlLinkedIn() != null) {
            detailUser.setUrlLinkedIn(detailUserRequest.getUrlLinkedIn());
        }
        if (detailUserRequest.getGenero() != null) {
            detailUser.setGenero(detailUserRequest.getGenero());
        }
        if (detailUserRequest.getPais() != null) {
            detailUser.setPais(detailUserRequest.getPais());
        }

        detailUserRepository.save(detailUser);
        userRepository.save(user);

        return detailUserMapper.convertToDTO(detailUser);
    }

    @Transactional
    public DetailUserResponseDTO updateSpecificFields(Integer userId, String urlLinkedIn, String genero, String pais) {
        DetailUser detailUser = detailUserRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException("Detalles no encontrados para el usuario con ID: " + userId));

        if (urlLinkedIn != null) {
            detailUser.setUrlLinkedIn(urlLinkedIn);
        }
        if (genero != null) {
            detailUser.setGenero(genero);
        }
        if (pais != null) {
            detailUser.setPais(pais);
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

    @Transactional(readOnly = true)
    public boolean detailUserExistsForUser(Integer userId) {
        return detailUserRepository.findByUser_Id(userId).isPresent();
    }
}
