package com.TecnologyGroup.Event_Tecnology.mapper;

import com.TecnologyGroup.Event_Tecnology.model.dto.PagoRequestDTO;
import com.TecnologyGroup.Event_Tecnology.model.dto.PagoResponseDTO;
import com.TecnologyGroup.Event_Tecnology.model.entity.Pago;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PagoMapper {

    private final ModelMapper modelMapper = new ModelMapper();

    public Pago convertToEntity(PagoRequestDTO pagoRequestDTO) {
        return modelMapper.map(pagoRequestDTO, Pago.class);
    }

    public PagoResponseDTO convertToDTO(Pago pago) {
        return modelMapper.map(pago, PagoResponseDTO.class);
    }
}
