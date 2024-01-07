package ch07.card;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StubCardNumberValidator implements CardNumberValidator {
    private String invalidNo;
    private String theftNo;

    @Override
    public CardValidity validate(String cardNumber) {
        if (invalidNo != null && invalidNo.equals(cardNumber)) {
            return CardValidity.INVALID;
        }
        if (theftNo != null && theftNo.equals(cardNumber)) {
            return CardValidity.THEFT;
        }
        return CardValidity.VALID;
    }
}
