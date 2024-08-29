package org.vgn.services;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vgn.dtos.ClientDTO;
import org.vgn.entities.ClientEntity;
import org.vgn.exceptions.BusinessException;
import org.vgn.mappers.ClientMapper;
import org.vgn.repositories.ClientRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class ClientService {

    private static final Logger log = LoggerFactory.getLogger(ClientService.class);

    @Inject
    ClientRepository clientRepository;

    @Transactional
    public ClientDTO createClient(ClientDTO clientDTO) {
        
        try {
            ClientEntity newClientEntity = ClientMapper.INSTANCE.toClientEntity(clientDTO);
            clientRepository.persist(newClientEntity);
            clientDTO.setId(newClientEntity.id);
            log.info("ClientService.createClient: client inserted: {}", clientDTO.getId());

            return clientDTO;
        } catch (Exception e) {
            log.error("ClientService.createClient: error: client not inserted: {}", clientDTO.getEmail());
            throw new RuntimeException("client not inserted");
        }

    }

    @Transactional
    public List<ClientDTO> getAllClients() {
        try {
            return ClientMapper.INSTANCE.toClientDTOList(clientRepository.listAll());
        } catch (Exception e) {
            log.error("ClientService.getAllClients: error" + e.getMessage());
            throw new RuntimeException("not possible to get all clients");
        }
    }

    @Transactional
    public ClientDTO getClientEntityByUUID(String clientId) {
        try {
            ClientEntity clientEntity = clientRepository.findByUUID(UUID.fromString(clientId));
            if (clientEntity == null) {
                return null;
            }
            return ClientMapper.INSTANCE.toClientDTO(clientEntity);

        } catch (Exception e) {
            log.error("ClientService.getClientEntityById: error find to client by id: {} - message: {}", clientId, e.getMessage());
            throw new BusinessException(e.getMessage());
        }        
    }

}
