package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.mapper.HabilidadMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.HabilidadResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Habilidad;
import com.TecnologyGroup.Event_Tecnology.repository.HabilidadRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class HabilidadService {

    private final HabilidadRepository habilidadRepository;
    private final HabilidadMapper habilidadMapper;

    @Transactional(readOnly = true)
    public List<HabilidadResponseDTO> getAllHabilidades() {
        List<Habilidad> habilidades = habilidadRepository.findAll();
        return habilidadMapper.convertToListDTO(habilidades);
    }

    public HabilidadResponseDTO getHabiliadadById(Integer id) {
        Habilidad habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el identificador: " + id));
        return habilidadMapper.convertToDTO(habilidad);
    }

    @Transactional
    public HabilidadResponseDTO createHabilidad(HabilidadRequestDTO habilidadRequestDTO) {
        if (habilidadRepository.existsByHabilidadNombre(habilidadRequestDTO.getHabilidadNombre())) {
            throw new EntityExistsException("La habilidad ya existe");
        }
        Habilidad habilidad = habilidadMapper.convertToEntity(habilidadRequestDTO);
        habilidadRepository.save(habilidad);
        return habilidadMapper.convertToDTO(habilidad);
    }

    @Transactional(readOnly = true)
    public Habilidad getHabilidadByName(String nombre) {
        return habilidadRepository.findByHabilidadNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el nombre: " + nombre));
    }

    @Transactional
    public HabilidadResponseDTO updateHabilidad(Integer id, HabilidadRequestDTO habilidadRequestDTO) {
        Habilidad habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el identificador: " + id));

        habilidad.setHabilidadNombre(habilidadRequestDTO.getHabilidadNombre());

        habilidad = habilidadRepository.save(habilidad);
        return habilidadMapper.convertToDTO(habilidad);
    }


    @Transactional
    public void deleteHabilidad(Integer id) {
        Habilidad habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el identificador: " + id));
        habilidadRepository.delete(habilidad);
    }
}