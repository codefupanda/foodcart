package com.foodcart.account.resources;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.ws.rs.core.MediaType;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static org.hamcrest.Matchers.*;

@QuarkusTest
@TestHTTPEndpoint(AccountResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccountResourceTest {

    @Test
    @Order(1)
    public void testAccountBeforeCreation() {
        RestAssured
                .given()
                .when()
                .get("/557f012c-f123-446f-9b38-3a4597f0a664")
                .then()
                .assertThat()
                .statusCode(404);
    }

    @Test
    @Order(2)
    public void testHelloWorld() {
        RestAssured
                .given()
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);
    }

    @Test
    @Order(3)
    public void testAccountCreationWithoutPhoneNumber() {
        JsonObject jsonItem = Json
                .createObjectBuilder()
                .add("name", "firstName")
                .build();

        RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonItem.toString())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(400);
    }

    @Test
    @Order(4)
    public void testAccountCreation() {
        JsonObject jsonItem = Json
                .createObjectBuilder()
                .add("name", "firstName")
                .add("phoneNumbers", Json.createArrayBuilder().add(Json.createObjectBuilder()
                        .add("countryCode", "+91")
                        .add("phoneNumber", "97875628292")
                        .add("primary", true)
                        .build()))
                .build();

        ValidatableResponse validatableResponse = RestAssured
                .given()
                .contentType(MediaType.APPLICATION_JSON)
                .body(jsonItem.toString())
                .when()
                .post()
                .then()
                .assertThat()
                .statusCode(201);

        String location = validatableResponse.extract().header("Location");
        String[] split = location.split("/");

        RestAssured
                .given()
                .when()
                .get(split[split.length-1])
                .then()
                .assertThat()
                .statusCode(200)
                .body("name", equalTo("firstName"))
                .body("phoneNumbers.size()", is(1))
                .body("phoneNumbers[0].countryCode", equalTo("+91"))
                .body("phoneNumbers[0].phoneNumber", equalTo("97875628292"))
                .body("phoneNumbers[0].primary", equalTo(true));
    }
}
