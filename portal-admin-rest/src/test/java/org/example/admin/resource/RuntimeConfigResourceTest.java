package org.example.admin.resource;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@QuarkusTest
@Tag("integration")
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