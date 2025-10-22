package com.example.psp.dto;

import com.example.psp.data.TransactionStatus;

import java.util.UUID;

public record PaymentResponse(UUID transactionId, TransactionStatus status, String message) {
}
