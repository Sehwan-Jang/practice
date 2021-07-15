package playground.web;

import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import playground.dto.IdDto;
import playground.dto.PlayerRequest;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PlayerAcceptanceTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = this.port;
    }

    @Test
    void postPlayerUsingRequestBody() {
        플레이어_등록되어_있음("aaron", 14);

        IdDto request = new IdDto(1L);

        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .param("id", 1L)
                .when()
                .get("/players-get")
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }

    private void 플레이어_등록되어_있음(String name, int backNumber) {
        PlayerRequest request = new PlayerRequest(name, backNumber);
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .body(request)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .post("/players")
                .then().log().all()
                .extract();

        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    void delete() {
        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .when()
                .delete("/players/1")
                .then().log().all()
                .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}