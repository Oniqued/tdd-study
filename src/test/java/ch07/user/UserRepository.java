package ch07.user;

public interface UserRepository {
    void save(User user);

    User findById(String id);
}
