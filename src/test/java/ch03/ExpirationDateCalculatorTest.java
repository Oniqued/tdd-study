package ch03;

import org.example.ch03.ExpirationDateCalculator;
import org.example.ch03.PaymentData;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExpirationDateCalculatorTest {
    private ExpirationDateCalculator calculator = new ExpirationDateCalculator();

    @Test
    void 만료일이언제임() {
        LocalDate now = LocalDate.of(2019, 1, 30);
        LocalDate afterMonth = now.plusMonths(2);
        assertEquals(LocalDate.of(2019,3,30), afterMonth);
    }

    @Test
    @DisplayName("만원을 납부하면 한달 뒤가 만료일이 된다")
    void 만원_납부하면_한달_뒤가_만료일이_됨() {
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019,3,1))
                        .paymentAmount(10_000)
                        .build(),
                LocalDate.of(2019, 4, 1)
        );
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019,5,5))
                        .paymentAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 5)
        );
    }

    @Test
    void 납부일과_한달_뒤_일자가_같지_않음() {
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019,1,31))
                        .paymentAmount(10_000)
                        .build(),
                LocalDate.of(2019, 2, 28)
        );
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019,5,31))
                        .paymentAmount(10_000)
                        .build(),
                LocalDate.of(2019, 6, 30)
        );
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2020,1,31))
                        .paymentAmount(10_000)
                        .build(),
                LocalDate.of(2020, 2, 29)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_만원_납부() {
        PaymentData paymentData = PaymentData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 31))
                .billingDate(LocalDate.of(2019, 2, 28))
                .paymentAmount(10_000)
                .build();
        assertExpirationDate(paymentData, LocalDate.of(2019, 3, 31));

        PaymentData paymentData2 = PaymentData.builder()
                .firstBillingDate(LocalDate.of(2019, 1, 30))
                .billingDate(LocalDate.of(2019, 2, 28))
                .paymentAmount(10_000)
                .build();
        assertExpirationDate(paymentData2, LocalDate.of(2019, 3, 30));

        PaymentData paymentData3 = PaymentData.builder()
                .firstBillingDate(LocalDate.of(2019, 5, 31))
                .billingDate(LocalDate.of(2019, 6, 30))
                .paymentAmount(10_000)
                .build();

        assertExpirationDate(paymentData3, LocalDate.of(2019, 7, 31));
    }

    @Test
    void 이만원_이상_납부하면_비례해서_만료일_계산() {
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .paymentAmount(20_000)
                        .build(),
                LocalDate.of(2019, 5, 1)
        );

        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .paymentAmount(30_000)
                        .build(),
                LocalDate.of(2019, 6, 1)
        );

        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019, 3, 1))
                        .paymentAmount(70_000)
                        .build(),
                LocalDate.of(2019, 10, 1)
        );
    }

    @Test
    void 첫_납부일과_만료일_일자가_다를때_이만원_이상_납부() {
        assertExpirationDate(
                PaymentData.builder()
                        .firstBillingDate(LocalDate.of(2019, 1, 31))
                        .billingDate(LocalDate.of(2019, 2, 28))
                        .paymentAmount(20_000)
                        .build(),
                LocalDate.of(2019, 4, 30)
        );
    }

    @Test
    void 십만원을_납부하면_1년_제공() {
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019, 1, 28))
                        .paymentAmount(100_000)
                        .build(),
                LocalDate.of(2020, 1, 28)
        );
    }

    @Test
    void 십만원_이상을_납부하면_1년_이상_제공() {
        assertExpirationDate(
                PaymentData.builder()
                        .billingDate(LocalDate.of(2019, 5, 5))
                        .paymentAmount(130_000)
                        .build(), LocalDate.of(2020, 8, 5)
        );
    }

    private void assertExpirationDate(PaymentData paymentData, LocalDate expectedExpirationDate) {
        LocalDate expirationDate = calculator.calculateExpirationDate(paymentData);
        assertEquals(expectedExpirationDate, expirationDate);
    }


}
