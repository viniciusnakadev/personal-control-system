package org.vgn.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.vgn.dtos.ClientDTO;
import org.vgn.entities.ClientEntity;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    
    ClientDTO toClientDTO(ClientEntity clientEntity);
    ClientEntity toClientEntity(ClientDTO clientDTO);
    List<ClientDTO> toClientDTOList(List<ClientEntity> clientEntityList);
    List<ClientEntity> toClientEntityList(List<ClientDTO> clientDTOList);

}
