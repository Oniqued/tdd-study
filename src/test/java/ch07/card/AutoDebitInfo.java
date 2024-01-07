package ch07.card;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AutoDebitInfo {
    private String userId;
    private String cardNumber;
    private LocalDateTime time;

    public AutoDebitInfo(String userId, String cardNumber, LocalDateTime time) {
        this.userId = userId;
        this.cardNumber = cardNumber;
        this.time = time;
    }

    public void changeCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }
}
