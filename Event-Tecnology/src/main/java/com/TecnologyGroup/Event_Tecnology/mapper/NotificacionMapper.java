package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.NotificacionResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Notificacion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class NotificacionMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Notificacion convertToEntity(NotificacionRequestDTO notificacionRequestDTO) {
        return modelMapper.map(notificacionRequestDTO, Notificacion.class);
    }

    public NotificacionResponseDTO convertToDTO(Notificacion notificacion) {
        return modelMapper.map(notificacion, NotificacionResponseDTO.class);
    }
}
