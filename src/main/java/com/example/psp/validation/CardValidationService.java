package com.example.psp.validation;

import com.example.psp.exception.ValidationException;
import org.apache.commons.validator.routines.checkdigit.LuhnCheckDigit;
import org.springframework.stereotype.Service;

@Service
public class CardValidationService {

    public void validateCardNumber(String cardNumber) {
        if (!LuhnCheckDigit.LUHN_CHECK_DIGIT.isValid(cardNumber)){
            throw new ValidationException("Invalid card number (Luhn check failed)");
        }
    }
}
