package org.example.client.resource;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RuntimeConfigResourceTest {

    @Test
    void getConfig() {
        given()
                .when().get(RuntimeConfigResource.BASE_URL)
                .then()
                .statusCode(200)
                .body(is("Some Config"));
    }
}