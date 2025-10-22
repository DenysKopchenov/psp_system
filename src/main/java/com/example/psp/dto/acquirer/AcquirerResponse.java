package com.example.psp.dto.acquirer;

import com.example.psp.data.TransactionStatus;

import java.util.UUID;

public record AcquirerResponse(UUID transactionId, TransactionStatus transactionStatus, String acquirerName) {

}
