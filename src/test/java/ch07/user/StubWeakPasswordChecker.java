package ch07.user;

public class StubWeakPasswordChecker implements WeakPasswordChecker {
    private boolean weak;

    public void setWeak(boolean weak) {
        this.weak = weak;
    }

    @Override
    public boolean isWeak(String pw) {
        return weak;
    }

    @Override
    public boolean checkPasswordWeak(String pw) {
        return false;
    }
}
