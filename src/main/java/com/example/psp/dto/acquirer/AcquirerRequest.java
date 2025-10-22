package com.example.psp.dto.acquirer;

import java.util.UUID;

public record AcquirerRequest(UUID transactionId,
                              String cardNumber) {
}
