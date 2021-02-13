package com.publicisSapient.luhnSafeCard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    private String id;
    private String name;
    private String number;
    private BigDecimal limit;
}
