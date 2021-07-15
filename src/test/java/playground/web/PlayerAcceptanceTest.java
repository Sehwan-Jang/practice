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
import playground.dto.PlayerResponse;

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
        플레이어_등록_요청("aaron", 14);

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

    private ExtractableResponse<Response> 플레이어_등록_요청 (String name, int backNumber) {
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
        return response;
    }

    private PlayerResponse 플레이어_등록됨(String name, int backNumber) {
        return 플레이어_등록_요청(name, backNumber).as(PlayerResponse.class);
    }

    @Test
    void delete() {
        PlayerResponse playerResponse = 플레이어_등록됨("aaron", 14);

        ExtractableResponse<Response> response = RestAssured
                .given().log().all()
                .when()
                .delete("/players/" + playerResponse.getId())
                .then().log().all()
                .extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.NO_CONTENT.value());
    }
}