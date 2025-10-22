package com.example.psp.service.aquirer;

import com.example.psp.data.AcquirerType;
import com.example.psp.dto.acquirer.AcquirerRequest;
import com.example.psp.dto.acquirer.AcquirerResponse;

public interface Acquirer {

    AcquirerType getType();

    AcquirerResponse sendRequest(AcquirerRequest request);
}
