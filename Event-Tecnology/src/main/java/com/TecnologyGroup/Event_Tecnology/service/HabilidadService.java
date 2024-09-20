package com.TecnologyGroup.Event_Tecnology.service;

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

    @Transactional(readOnly = true)
    public List<Habilidad> getAllHabilidades() {
        return habilidadRepository.findAll();
    }

    @Transactional
    public Habilidad createHabilidad(Habilidad habilidad) {
        if (habilidadRepository.existsByHabilidadNombre(habilidad.getHabilidadNombre())) {
            throw new EntityExistsException("La habilidad ya existe");
        }
        return habilidadRepository.save(habilidad);
    }

    @Transactional(readOnly = true)
    public Habilidad getHabilidadByName(String nombre) {
        return habilidadRepository.findByHabilidadNombre(nombre)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el nombre: " + nombre));
    }

    @Transactional
    public Habilidad updateHabilidad(Integer id, Habilidad habilidadActualizada) {
        Habilidad habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el identificador: " + id));

        if (habilidadRepository.existsByHabilidadNombre(habilidadActualizada.getHabilidadNombre())) {
            throw new EntityExistsException("La habilidad ya existe con ese nombre");
        }

        habilidad.setHabilidadNombre(habilidadActualizada.getHabilidadNombre());
        return habilidadRepository.save(habilidad);
    }

    @Transactional
    public void deleteHabilidad(Integer id) {
        Habilidad habilidad = habilidadRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Habilidad no encontrada con el identificador: " + id));
        habilidadRepository.delete(habilidad);
    }
}