package ch08;

public interface CustomerRepository {
    Customer findOne(String id);
}
