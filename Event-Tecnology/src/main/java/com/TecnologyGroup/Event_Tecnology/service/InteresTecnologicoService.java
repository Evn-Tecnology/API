package com.TecnologyGroup.Event_Tecnology.service;

import com.TecnologyGroup.Event_Tecnology.model.entity.InteresTecnologico;
import com.TecnologyGroup.Event_Tecnology.repository.InteresTecnologicoRepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class InteresTecnologicoService {

    private final InteresTecnologicoRepository interesTecnologicoRepository;

    @Transactional(readOnly = true)
    public List<InteresTecnologico> getAllInteresesTecnologicos() {
        return interesTecnologicoRepository.findAll();
    }

    @Transactional
    public InteresTecnologico createInteresTecnologico(InteresTecnologico interesTecnologico) {
        if (interesTecnologicoRepository.existsByInteresTecNombre(interesTecnologico.getInteresTecNombre())) {
            throw new EntityExistsException("El interés tecnológico ya existe");
        }
        return interesTecnologicoRepository.save(interesTecnologico);
    }

    @Transactional(readOnly = true)
    public InteresTecnologico getInteresByName(String nombre) {
        return interesTecnologicoRepository.findByInteresTecNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Interés tecnológico no encontrado con el nombre: " + nombre));
    }

    @Transactional
    public InteresTecnologico updateInteresTecnologico(Integer id, InteresTecnologico interesTecnologicoActualizado) {
        InteresTecnologico interesTecnologico = interesTecnologicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interés tecnológico no encontrado con el identificador: " + id));

        if (interesTecnologicoRepository.existsByInteresTecNombre(interesTecnologicoActualizado.getInteresTecNombre())) {
            throw new EntityExistsException("El interés tecnológico ya existe con ese nombre");
        }

        interesTecnologico.setInteresTecNombre(interesTecnologicoActualizado.getInteresTecNombre());
        return interesTecnologicoRepository.save(interesTecnologico);
    }

    @Transactional
    public void deleteInteresTecnologico(Integer id) {
        InteresTecnologico interesTecnologico = interesTecnologicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Interés tecnológico no encontrado con el identificador: " + id));
        interesTecnologicoRepository.delete(interesTecnologico);
    }
}
