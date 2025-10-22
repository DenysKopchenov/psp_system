package com.example.psp.dto;

import com.example.psp.constants.RegexpConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PaymentRequest {

    @NotBlank
    @Pattern(regexp = RegexpConstants.CARD_PATTERN, message = "Card number must be 12–19 digits")
    private String cardNumber;

    @NotBlank
    @Pattern(regexp = RegexpConstants.EXPIRY_PATTERN, message = "Expiry must be in MM/YY format")
    private String expiry;

    @NotBlank
    @Pattern(regexp = RegexpConstants.CVV_PATTERN, message = "Invalid CVV")
    private String cvv;

    @Positive
    private Long amount;

    @NotBlank
    @Pattern(regexp = RegexpConstants.CURRENCY_PATTERN, message = "Currency must be 3 uppercase letters")
    private String currency;

    @NotBlank
    private String merchantId;
}
