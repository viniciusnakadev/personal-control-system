package org.vgn.resources;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.vgn.dtos.ClientDTO;
import org.vgn.exceptions.BusinessException;
import org.vgn.exceptions.NotFoundException;
import org.vgn.services.ClientService;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/clients")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    private static final Logger log = LoggerFactory.getLogger(ClientResource.class);

    @Inject
    ClientService clientService;

    @POST
    public Response createClient(ClientDTO clientDTO) {

        if (clientDTO == null || clientDTO.getName().isBlank() || clientDTO.getEmail().isBlank() ||
                clientDTO.getDateOfBirth() == null) {
            throw new BusinessException("Data of the Client is incosistent");            
        }

        ClientDTO newClientDTO = clientService.createClient(clientDTO);
        return Response.status(Response.Status.CREATED).entity(newClientDTO).build();
    }

    @GET
    @Path("/all")
    public Response getAllClients() {
        List<ClientDTO> clientDTOList = clientService.getAllClients();
        if (clientDTOList == null) {
            throw new RuntimeException("error when getting all clients");
        }
        return Response.status(Response.Status.OK).entity(clientDTOList).build();
    }

    @GET
    @Path("/{id}")
    public Response getClientById(@PathParam("id") String clientId) {
        if (clientId.isBlank()) {
            throw new BusinessException("clientId is blank or null");
        }

        ClientDTO clientDTO = clientService.getClientEntityByUUID(clientId) ;
        if (clientDTO == null) {
            log.info("client not found: " + clientId);
            throw new NotFoundException("client not found");
        }

        return Response.status(Response.Status.OK).entity(clientDTO).build();
    }

}
