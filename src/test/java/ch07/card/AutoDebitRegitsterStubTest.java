package ch07.card;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AutoDebitRegitsterStubTest {
    private AutoDebitRegister register;
    private StubCardNumberValidator stubValidator;
    private StubAutoDebitInfoRepository stubRepository;

    @BeforeEach
    void setUp() {
        stubValidator = new StubCardNumberValidator();
        stubRepository = new StubAutoDebitInfoRepository();
        register = new AutoDebitRegister(stubValidator, stubRepository);
    }

    @Test
    void invalidCard() {
        stubValidator.setInvalidNo("123123123131321");

        AutoDebitReq req = new AutoDebitReq("user1", "123123123131321");
        RegisterResult result = register.register(req);

        assertEquals(RegisterResult.INVALID, result.getValidity());
    }

    @Test
    void theftCard() {
        stubValidator.setTheftNo("123123879");

        AutoDebitReq req = new AutoDebitReq("user1", "123123879");
        RegisterResult result = register.register(req);

        assertEquals(RegisterResult.THEFT, result.getValidity());
    }
}
