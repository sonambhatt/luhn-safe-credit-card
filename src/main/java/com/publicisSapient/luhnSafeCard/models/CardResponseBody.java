package com.publicisSapient.luhnSafeCard.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class CardResponseBody {
    private String id;
    private String name;
    private String number;
    private BigDecimal credit;

    public CardResponseBody(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.number = card.getNumber();
        this.credit = card.getCredit();
    }
}
