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
        Card card = new Card("", "randomName","randomNumber", BigDecimal.ZERO);
        Mockito.when(service.register(eq(card))).thenReturn(card);

        this.mockMvc.perform(post("/cards")
            .contentType(APPLICATION_JSON_VALUE)
            .content(objectMapper.writeValueAsString(card))
            .characterEncoding("utf-8"))
            .andExpect(status().isCreated())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(content().json(objectMapper.writeValueAsString(card)))
            .andReturn();
    }

    @Test
    @SneakyThrows
    void shouldFetchAllCards() {
        Card firstCard = new Card("", "firstRandomName","firstRandomNumber", BigDecimal.ZERO);
        Card secondCard = new Card("", "secondRandomName","secondRandomNumber", BigDecimal.ZERO);
        List<Card> allCards = List.of(firstCard, secondCard);

        Mockito.when(service.getAll()).thenReturn(allCards);

        this.mockMvc.perform(get("/cards"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(APPLICATION_JSON_VALUE))
            .andExpect(content().json(objectMapper.writeValueAsString(allCards)))
            .andReturn();
    }
}
