package com.example.psp.service.aquirer;

import com.example.psp.data.AcquirerType;
import com.example.psp.data.TransactionStatus;
import com.example.psp.dto.acquirer.AcquirerRequest;
import com.example.psp.dto.acquirer.AcquirerResponse;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class AcquirerB implements Acquirer {
    @Override
    public AcquirerType getType() {
        return AcquirerType.ODD;
    }

    @Override
    public AcquirerResponse sendRequest(AcquirerRequest request) {
        String cardNumber = request.cardNumber();
        Objects.requireNonNull(cardNumber, "Card number must not be null");

        TransactionStatus transactionStatus = getTransactionResult(cardNumber);

        return new AcquirerResponse(request.transactionId(), transactionStatus, this.getClass().getSimpleName());
    }

    private TransactionStatus getTransactionResult(String cardNumber) {
        int lastDigit = Character.getNumericValue(cardNumber.charAt(cardNumber.length() - 1));
        return (lastDigit % 2 == 0) ? TransactionStatus.APPROVED : TransactionStatus.DENIED;
    }
}
