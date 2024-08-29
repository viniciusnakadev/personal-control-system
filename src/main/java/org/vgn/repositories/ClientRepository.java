package org.vgn.repositories;

import java.util.UUID;

import org.vgn.entities.ClientEntity;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<ClientEntity> {

    public ClientEntity findByUUID(UUID id) {
        return find("id", id).firstResult();
    }

}
