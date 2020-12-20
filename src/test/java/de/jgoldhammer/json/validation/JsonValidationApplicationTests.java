package de.jgoldhammer.json.validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class JsonValidationApplicationTests {

    private MockMvc mockMvc;

    @Autowired
    JsonValidationApplicationTests(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void shouldSucceedForKnownJsonModel() throws Exception {

        JsonExampleModel jsonExampleModel = new JsonExampleModel();
        jsonExampleModel.name = "Spring Boot";

        mockMvc.perform(MockMvcRequestBuilders.post("/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(jsonExampleModel))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    void shouldFailForUnknownJsonModel() throws Exception {

        UnknownJsonExampleModel unknownJsonModel = new UnknownJsonExampleModel();
        unknownJsonModel.name = "Spring Boot";
        unknownJsonModel.age = 2;

        mockMvc.perform(MockMvcRequestBuilders.post("/json")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJson(unknownJsonModel))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is4xxClientError());

    }

    private String asJson(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
