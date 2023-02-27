package com.example.mostvaluableplayer.controller;

import com.example.mostvaluableplayer.model.Sportsman;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class TournamentControllerTest {

    @Autowired
    MockMvc mockMvc;

    String uri = "/tournament/mvp?path=src/main/resources/games";

    @Test
    void shouldReturn200IfGameStatsExistsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get(uri)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
    }

    @Test
    void shouldReturn404IfGameStatsNotExistsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(get("/wrong/uri")
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(404, status);
    }

    @Test
    void shouldReturnMvpIfGameStatsExistsTest() throws Exception {
        Sportsman expectedMvp = new Sportsman("nick3", 54);

        MvcResult mvcResult = mockMvc.perform(get(uri))
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        Sportsman actualMvp = mapFromJson(content, Sportsman.class);

        assertEquals(expectedMvp, actualMvp);
    }

    private <T> T mapFromJson(String json, Class<T> clazz)
            throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, clazz);
    }
}