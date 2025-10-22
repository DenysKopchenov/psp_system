package com.example.psp.data;

import lombok.Builder;
import lombok.Value;
import lombok.With;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
public class Transaction {
    UUID id;
    String merchantId;
    String maskedCard;
    String bin;
    String expiry;
    Long amount;
    String currency;
    @With
    TransactionStatus status;
    LocalDateTime createdAt;
    @With
    LocalDateTime updatedAt;
}
