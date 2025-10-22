package com.example.psp.persistence.entity;

import com.example.psp.data.TransactionStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record TransactionEntity(UUID id,
                                String merchantId,
                                String maskedCard,
                                String bin,
                                String expiry,
                                Long amount,
                                String currency,
                                TransactionStatus status,
                                LocalDateTime createdAt,
                                LocalDateTime updatedAt) {

}
