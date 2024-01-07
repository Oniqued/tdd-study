package ch07.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class AutoDebitRegisterFakeTest {
    private AutoDebitRegister register;
    private CardNumberValidator cardNumberValidator;
    private AutoDebitInfoRepository repository;

    @BeforeEach
    void setUp() {
        cardNumberValidator = new StubCardNumberValidator();
        repository = new MemoryAutoDebitInfoRepository();
        register = new AutoDebitRegister(cardNumberValidator, repository);
    }

    @Test
    void alreadyRegisteredInfoUpdated() {
        repository.save(new AutoDebitInfo("user1", "123123123", LocalDateTime.now()));

        AutoDebitReq req = new AutoDebitReq("user1", "23123123");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("23123123", saved.getCardNumber());
    }

    @Test
    void notYetRegisteredNewInfoRegistered() {
        AutoDebitReq req = new AutoDebitReq("user1", "12213123123123");
        RegisterResult result = register.register(req);

        AutoDebitInfo saved = repository.findOne("user1");
        assertEquals("12213123123123", saved.getCardNumber());
    }
}
