package com.publicisSapient.luhnSafeCard.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id private String id;
    private String name;
    private String number;
    private BigDecimal limit;
}
