package com.example.psp.persistence.repository;

import com.example.psp.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepository {

    private final Map<UUID, TransactionEntity> storage = new ConcurrentHashMap<>();

    public TransactionEntity save(TransactionEntity tx) {
        storage.put(tx.id(), tx);
        return tx;
    }
}
