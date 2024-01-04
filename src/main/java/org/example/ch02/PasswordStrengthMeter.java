package org.example.ch02;

public class PasswordStrengthMeter {
    public PasswordStrength meter(String s) {
        if (s == null || s.isEmpty()) {
            return PasswordStrength.INVALID;
        }
        int strength = getStrength(s);
        if (strength <= 1) {
            return PasswordStrength.WEAK;
        }
        if (strength == 2) {
            return PasswordStrength.NORMAL;
        }
        return PasswordStrength.STRONG;
    }

    private int getStrength(String s) {
        int strength = 0;
        if (isEnoughLength(s)) {
            strength++;
        }
        if (isContainsNum(s)) {
            strength++;
        }
        if (isContainsUppercase(s)) {
            strength++;
        }
        return strength;
    }

    private boolean isEnoughLength(String s) {
        return s.length() >= 8;
    }

    private boolean isContainsUppercase(String s) {
        for (char ch : s.toCharArray()) {
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    private boolean isContainsNum(String s) {
        for (char ch : s.toCharArray()) {
            if (ch >= '0' && ch <= '9') {
                return true;
            }
        }
        return false;
    }
}
