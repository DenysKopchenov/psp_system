package com.example.psp.constants;

public class RegexpConstants {

    public static final String CARD_PATTERN = "\\d{12,19}";
    public static final String EXPIRY_PATTERN = "^(0[1-9]|1[0-2])/\\d{2,4}$";
    public static final String CVV_PATTERN = "\\d{3,4}";
    public static final String CURRENCY_PATTERN = "^[A-Z]{3}$";
}
