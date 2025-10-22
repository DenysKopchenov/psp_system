package com.example.psp.persistence.service;

import com.example.psp.data.Transaction;
import com.example.psp.persistence.entity.TransactionEntity;
import com.example.psp.persistence.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionPersistenceService {

    private final TransactionRepository repository;

    public Transaction save(Transaction tx) {
        TransactionEntity save = repository.save(toEntity(tx));
        return toTransaction(save);
    }

    private Transaction toTransaction(TransactionEntity entity) {
        return Transaction.builder()
                .id(entity.id())
                .merchantId(entity.merchantId())
                .maskedCard(entity.maskedCard())
                .bin(entity.bin())
                .expiry(entity.expiry())
                .amount(entity.amount())
                .currency(entity.currency())
                .status(entity.status())
                .createdAt(entity.createdAt())
                .updatedAt(entity.updatedAt())
                .build();
    }

    TransactionEntity toEntity(Transaction tx) {
        return new TransactionEntity(
                tx.getId(),
                tx.getMerchantId(),
                tx.getMaskedCard(),
                tx.getBin(),
                tx.getExpiry(),
                tx.getAmount(),
                tx.getCurrency(),
                tx.getStatus(),
                tx.getCreatedAt(),
                tx.getUpdatedAt()
        );
    }
}
