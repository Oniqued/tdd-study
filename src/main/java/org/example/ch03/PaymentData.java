package org.example.ch03;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class PaymentData {
    private LocalDate firstBillingDate;
    private LocalDate billingDate;
    private int paymentAmount;

    public PaymentData(LocalDate firstBillingDate, LocalDate billingDate, int paymentAmount) {
        this.firstBillingDate = firstBillingDate;
        this.billingDate = billingDate;
        this.paymentAmount = paymentAmount;
    }
}
