package com.example.psp.service;

import com.example.psp.data.Transaction;
import com.example.psp.data.TransactionStatus;
import com.example.psp.dto.PaymentRequest;
import com.example.psp.dto.PaymentResponse;
import com.example.psp.dto.acquirer.AcquirerRequest;
import com.example.psp.dto.acquirer.AcquirerResponse;
import com.example.psp.exception.ValidationException;
import com.example.psp.persistence.service.TransactionPersistenceService;
import com.example.psp.util.CardUtil;
import com.example.psp.validation.CardValidationService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentService {

    private final CardValidationService cardValidationService;
    private final TransactionPersistenceService persistenceService;
    private final AcquirerRouter router;

    public PaymentResponse processPayment(PaymentRequest req) {
        String cardNumber = req.getCardNumber();
        cardValidationService.validateCardNumber(cardNumber);

        Transaction tx = buildInitialTransaction(req, cardNumber);

        Transaction saved = persistenceService.save(tx);

        AcquirerResponse response = router.sendRequestToAcquirer(new AcquirerRequest(saved.getId(), cardNumber));

        Transaction updatedTx = saved.withStatus(response.transactionStatus())
                .withUpdatedAt(LocalDateTime.now());

        Transaction updated = persistenceService.save(updatedTx);

        return new PaymentResponse(updated.getId(), updated.getStatus(), "Routed to acquirer " + response.acquirerName());
    }

    private Transaction buildInitialTransaction(PaymentRequest req, String cardNumber) {
        return Transaction.builder()
                .id(UUID.randomUUID())
                .merchantId(req.getMerchantId())
                .maskedCard(CardUtil.maskCard(cardNumber))
                .bin(CardUtil.getBinFromCard(cardNumber))
                .expiry(req.getExpiry())
                .amount(req.getAmount())
                .currency(req.getCurrency())
                .status(TransactionStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
