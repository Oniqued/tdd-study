package org.example.ch03;

import java.time.LocalDate;
import java.time.YearMonth;

public class ExpirationDateCalculator {
    public LocalDate calculateExpirationDate(PaymentData paymentData) {
        int addedMonths = getMonthsByPaymentAmount(paymentData.getPaymentAmount());
        if (paymentData.getFirstBillingDate() != null) {
            return expirationDateUsingFirstBillingDate(paymentData, addedMonths);
        }
        return paymentData.getBillingDate().plusMonths(addedMonths);
    }

    private LocalDate expirationDateUsingFirstBillingDate(PaymentData paymentData, int addedMonths) {
        LocalDate candidateExp = paymentData.getBillingDate().plusMonths(addedMonths);
        if (!isSameDayOfMonth(paymentData.getFirstBillingDate(), candidateExp)) {
            final int dayLengthOfCandidateMonth = lastDayOfMonth(candidateExp);
            final int dayOfFirstBilling = paymentData.getFirstBillingDate().getDayOfMonth();
            if (dayLengthOfCandidateMonth < dayOfFirstBilling) {
                return candidateExp.withDayOfMonth(dayLengthOfCandidateMonth);
            }
            return candidateExp.withDayOfMonth(dayOfFirstBilling);
        }
        return candidateExp;
    }

    private boolean isSameDayOfMonth(LocalDate date1, LocalDate date2) {
        return date1.getDayOfMonth() == date2.getDayOfMonth();
    }

    private int lastDayOfMonth(LocalDate date) {
        return YearMonth.from(date).lengthOfMonth();
    }

    private int getMonthsByPaymentAmount(int paymentAmount) {
        if (paymentAmount >= 100_000) {
            return 12 * (paymentAmount / 100_000) + (paymentAmount % 100_000) / 10_000 ;
        }
        return paymentAmount / 10_000;
    }
}
