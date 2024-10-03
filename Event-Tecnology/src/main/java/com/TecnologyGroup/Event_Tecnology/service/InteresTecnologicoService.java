package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.mapper.InteresTecnologicoMapper;
import com.TecnologyGroup.Event_Tecnology.model.dto.InteresTecnologicoRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.InteresTecnologicoResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.RolResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.InteresTecnologico;
import com.TecnologyGroup.Event_Tecnology.model.entity.Rol;
import com.TecnologyGroup.Event_Tecnology.repository.InteresTecnologicoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InteresTecnologicoService {

    private final InteresTecnologicoRepository interesTecnologicoRepository;
    private final InteresTecnologicoMapper interesTecnologicoMapper;

    @Transactional(readOnly = true)
    public List<InteresTecnologicoResponseDTO> getAllInteresesTecnologicos() {
        List<InteresTecnologico> interesTecnologicos = interesTecnologicoRepository.findAll();
        return interesTecnologicoMapper.convertToListDTO(interesTecnologicos);
    }

    @Transactional(readOnly = true)
    public InteresTecnologicoResponseDTO getInteresById(Integer id) {
        InteresTecnologico interesTecnologico = interesTecnologicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interes tecnologico no encontrado con el identificador: " + id));
        return interesTecnologicoMapper.convertToDTO(interesTecnologico);
    }

    @Transactional
    public InteresTecnologicoResponseDTO createInteresTecnologico(InteresTecnologicoRequestDTO interesTecnologicoRequestDTO) {
        if (interesTecnologicoRepository.existsByInteresTecNombre(interesTecnologicoRequestDTO.getInteresTecnologicoNombre())) {
            throw new EntityExistsException("El interés tecnológico ya existe");
        }
        InteresTecnologico interesTecnologico = interesTecnologicoMapper.convertToEntity(interesTecnologicoRequestDTO);
        interesTecnologicoRepository.save(interesTecnologico);
        return interesTecnologicoMapper.convertToDTO(interesTecnologico);
    }

    @Transactional(readOnly = true)
    public InteresTecnologico getInteresByName(String nombre) {
        return interesTecnologicoRepository.findByInteresTecNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Interés tecnológico no encontrado con el nombre: " + nombre));
    }

    @Transactional
    public InteresTecnologicoResponseDTO updateInteresTecnologico(Integer id, InteresTecnologicoRequestDTO interesTecnologicoRequestDTO) {
        InteresTecnologico interesTecnologico = interesTecnologicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interés tecnológico no encontrado con el identificador: " + id));

        interesTecnologico.setInteresTecNombre(interesTecnologicoRequestDTO.getInteresTecnologicoNombre());

        interesTecnologico = interesTecnologicoRepository.save(interesTecnologico);
        return interesTecnologicoMapper.convertToDTO(interesTecnologico);
    }

    @Transactional
    public void deleteInteresTecnologico(Integer id) {
        InteresTecnologico interesTecnologico = interesTecnologicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interés tecnológico no encontrado con el identificador: " + id));
        interesTecnologicoRepository.delete(interesTecnologico);
    }
}
