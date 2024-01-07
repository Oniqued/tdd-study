package ch07.card;

public enum RegisterResult {
    INVALID, SUCCESS, THEFT;

    public RegisterResult getValidity() {
        return this;
    }

    public static RegisterResult error(CardValidity validity) {
        if (validity == CardValidity.THEFT) {
            return RegisterResult.THEFT;
        }
        return RegisterResult.INVALID;
    }

    public static RegisterResult success() {
        return RegisterResult.SUCCESS;
    }
}
