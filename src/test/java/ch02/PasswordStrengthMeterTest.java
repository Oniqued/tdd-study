package ch02;

import org.example.ch02.PasswordStrength;
import org.example.ch02.PasswordStrengthMeter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    private PasswordStrengthMeter meter = new PasswordStrengthMeter();

    private void assertStrength(String password, PasswordStrength expStr) {
        PasswordStrength result = meter.meter(password);
        assertEquals(expStr, result);
    }

    @Test
    @DisplayName("암호가 모든 조건을 충족하면 암호 강도는 강함이어야 함")
    void meetsAllCriteriaThenStrong() {
        assertStrength("ab12!@AB", PasswordStrength.STRONG);
        assertStrength("abc1!Add", PasswordStrength.STRONG);
    }

    @Test
    @DisplayName("조건을 두개 만족")
    void meetsOtherCriteriaExceptForLengthThenNormal() {
        assertStrength("an12!@A", PasswordStrength.NORMAL);
        assertStrength("Ab12!c", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("숫자를 포함하지 않고 나머지 조건은 충족하는 경우")
    void meetsOtherCriteriaExceptForNumberThenNormal() {
        assertStrength("ab!@ABasdasd", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("비밀번호가 null인 경우")
    void nullInputThenInvalid() {
        assertStrength(null, PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("비밀번호로 아무것도 넣지 않은 경우")
    void emptyInputThenInvalid() {
        assertStrength("", PasswordStrength.INVALID);
    }

    @Test
    @DisplayName("대문자를 포함하지 않고 나머지 조건을 만족하는 경우")
    void meetsOtherCriteriaExceptForUppercaseThenNormal() {
        assertStrength("ab12!@df", PasswordStrength.NORMAL);
    }

    @Test
    @DisplayName("길이가 8글자 이상인 조건만 충족하는 경우")
    void meetsOnlyLengthCriteriaThenWeak() {
        assertStrength("asdfasdfadsf", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("숫자 포함 조건만 충족하는 경우")
    void meetsOnlyNumCriteriaThenWeak() {
        assertStrength("12312", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("대문자 포함 조건만 충족하는 경우")
    void meetsOnlyUpperCriteriaThenWeak() {
        assertStrength("ASDASD", PasswordStrength.WEAK);
    }

    @Test
    @DisplayName("아무 조건도 충족하지 않은 경우")
    void meetsNoCriteriaThenWeak() {
        assertStrength("asd", PasswordStrength.WEAK);
    }
}
