package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.mapper.RolMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import com.TecnologyGroup.Event_Tecnology.repository.RolRepository;
import com.TecnologyGroup.Event_Tecnology.repository.UserRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class RolService {

    private final RolRepository rolRepository;
    private final RolMapper rolMapper;
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public List<RolResponseDTO> getAllRoles() {
        List<Rol> roles = rolRepository.findAll();
        return rolMapper.convertToListDTO(roles);
    }

    @Transactional(readOnly = true)
    public RolResponseDTO getRolById(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con el identificador: " + id));
        return rolMapper.convertToDTO(rol);
    }

    @Transactional(readOnly = true)
    public Rol getRolByName(String rolNombre) {
        return rolRepository.findByNombreRol(rolNombre)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con nombre: " + rolNombre));
    }

    @Transactional
    public RolResponseDTO createRol(RolRequestDTO rolRequestDTO) {
        if (rolRepository.existsByNombreRol(rolRequestDTO.getNombreRol())) {
            throw new EntityExistsException("El rol ya existe");
        }
        Rol rol = rolMapper.convertToEntity(rolRequestDTO);
        rolRepository.save(rol);
        return rolMapper.convertToDTO(rol);
    }

    @Transactional
    public RolResponseDTO updateRol(Integer id, RolRequestDTO rolRequestDTO) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con el identificador: " + id));

        rol.setNombreRol(rolRequestDTO.getNombreRol());

        rol = rolRepository.save(rol);
        return rolMapper.convertToDTO(rol);
    }

    @Transactional
    public void deleteRol(Integer id) {
        Rol rol = rolRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Rol no encontrado con el identificador: " + id));
        rolRepository.delete(rol);
    }
}