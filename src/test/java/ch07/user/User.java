package ch07.user;

import lombok.Getter;

@Getter
public class User {
    private String userId;
    private String pw;
    private String email;

    public User(String userId, String pw, String email) {
        this.userId = userId;
        this.pw = pw;
        this.email = email;
    }
}
