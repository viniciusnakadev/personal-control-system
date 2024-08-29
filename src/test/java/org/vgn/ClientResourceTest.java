package org.vgn;

import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;

@QuarkusTest
public class ClientResourceTest {

    @Test
    public void testGetAllClients() {
        given()
            .when().get("/clients/all")
            .then()
                .statusCode(200);
    }

    @Test
    public void testCreateClient() {

        String email = "testedev-".concat(String.valueOf(Instant.now().hashCode()).concat("@gmail.com"));

        String clientDTO = "{"
            + "\"name\": \"Teste Dev\","
            + "\"email\": \"" + email + "\","
            + "\"dateOfBirth\": \"1990-10-01\" }";

        given()
            .contentType(MediaType.APPLICATION_JSON)
            .body(clientDTO)
            .when().post("/clients")
            .then()
                .statusCode(201);
    }

    @Test
    public void testGetClientById() {
        UUID id = UUID.fromString("c76420e6-2412-4c53-b799-a025bd35e37c");
        given()
            .contentType(MediaType.APPLICATION_JSON)
            .when().get("/clients/" + id)
            .then()
                .statusCode(200);
    }

}
