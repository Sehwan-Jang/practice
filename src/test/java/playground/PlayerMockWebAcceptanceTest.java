package playground;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import playground.application.SimpleService;
import playground.dto.PlayerRequest;
import playground.dto.PlayerResponse;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
public class PlayerMockWebAcceptanceTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SimpleService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void addPlayer() throws Exception {
        String value = objectMapper.writeValueAsString(new PlayerRequest("aaron", 28));

        when(service.addPlayer(any())).thenReturn(1L);

        this.mockMvc
                .perform(post("/players")
                        .content(value)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/players/1"));
    }
}
