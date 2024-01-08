package ch07.user;

public interface WeakPasswordChecker {
    boolean isWeak(String pw);

    boolean checkPasswordWeak(String pw);
}
