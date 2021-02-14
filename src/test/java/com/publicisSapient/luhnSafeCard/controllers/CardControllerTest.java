package com.publicisSapient.luhnSafeCard.controllers;

import lombok.SneakyThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.publicisSapient.luhnSafeCard.models.Card;
import com.publicisSapient.luhnSafeCard.models.CardRequestBody;
import com.publicisSapient.luhnSafeCard.models.CardResponseBody;
import com.publicisSapient.luhnSafeCard.services.CardService;
import com.sun.tools.javac.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

class CardControllerTest {

    @Mock private CardService service;
    @InjectMocks private CardController controller;

    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = standaloneSetup(controller).build();
    }

    @Test
    @SneakyThrows
    void shouldRegisterCard() {
        CardRequestBody cardRequestBody = new CardRequestBody("randomName","randomNumber");
        CardResponseBody cardResponseBody = new CardResponseBody(new Card(cardRequestBody));
        Mockito.when(service.register(eq(cardRequestBody))).thenReturn(cardResponseBody);

        this.mockMvc.perform(post("/cards")
            .contentType(APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(cardResponseBody))
            .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(content().json(objectMapper.writeValueAsString(cardResponseBody)))
            .andReturn();
    }

    @Test
    @SneakyThrows
    void shouldFetchAllCards() {
        CardResponseBody firstCard = new CardResponseBody(new Card("", "firstRandomName","firstRandomNumber", BigDecimal.ZERO));
        CardResponseBody secondCard = new CardResponseBody(new Card("", "secondRandomName","secondRandomNumber", BigDecimal.ZERO));
        List<CardResponseBody> allCards = List.of(firstCard, secondCard);

        Mockito.when(service.getAll()).thenReturn(allCards);

        this.mockMvc.perform(get("/cards"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(content().json(objectMapper.writeValueAsString(allCards)))
            .andReturn();
    }
}
