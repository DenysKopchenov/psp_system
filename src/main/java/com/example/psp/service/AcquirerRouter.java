package com.example.psp.service;

import com.example.psp.data.AcquirerType;
import com.example.psp.dto.acquirer.AcquirerRequest;
import com.example.psp.dto.acquirer.AcquirerResponse;
import com.example.psp.service.aquirer.Acquirer;
import com.example.psp.util.CardUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
public class AcquirerRouter {

    private final Map<AcquirerType, Acquirer> acquirerMap;

    public AcquirerRouter(List<Acquirer> acquirers) {
        acquirerMap = acquirers.stream()
                .collect(Collectors.toMap(Acquirer::getType, Function.identity()));
    }

    public AcquirerResponse sendRequestToAcquirer(AcquirerRequest request) {
        Acquirer acquirer = getAcquirer(request.cardNumber());

        log.info("Send request to {}", acquirer.getClass().getSimpleName());
        return acquirer.sendRequest(request);
    }

    private Acquirer getAcquirer(String cardNumber) {
        String bin = CardUtil.getBinFromCard(cardNumber);
        int sum = bin.chars().map(Character::getNumericValue).sum();
        AcquirerType type = sum % 2 == 0 ? AcquirerType.EVEN : AcquirerType.ODD;

       return Optional.ofNullable(acquirerMap.get(type))
                .orElseThrow(() -> new IllegalStateException("No strategy for acquirer type: " + type));
    }

}
