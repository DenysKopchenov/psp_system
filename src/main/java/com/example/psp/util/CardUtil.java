package com.example.psp.util;

import lombok.experimental.UtilityClass;

import java.util.Objects;

@UtilityClass
public class CardUtil {

    public static String getBinFromCard(String cardNumber) {
        Objects.requireNonNull(cardNumber, "Card number must not be null");

        return cardNumber.substring(0, 6);
    }

    public static String maskCard(String card) {
        Objects.requireNonNull(card, "Card must not be null");

        return "*".repeat(card.length() - 4) + card.substring(card.length() - 4);
    }
}
