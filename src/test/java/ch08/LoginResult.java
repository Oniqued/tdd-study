package ch08;

public enum LoginResult {
    BAD_AUTH_KEY, AUTHENTICATED, FAILED
    ;

    public static LoginResult badAuthKey() {
        return BAD_AUTH_KEY;
    }

    public static LoginResult authenticated(Customer c) {
        return AUTHENTICATED;
    }

    public static LoginResult fail(int resp) {
        return FAILED;
    }
}
