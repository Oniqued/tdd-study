package ch07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

public class UserRegisterMockTest {
    private UserRegister userRegister;
    private EmailNotifier emailNotifier = mock(EmailNotifier.class);
    private UserRepository userRepository = new MemoryUserRepository();
    private WeakPasswordChecker passwordChecker = mock(WeakPasswordChecker.class);

    @BeforeEach
    void setUp() {
        userRegister = new UserRegister(passwordChecker, userRepository, emailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        given(passwordChecker.checkPasswordWeak("pw")).willReturn(true);

        assertThrows(WeakPasswordException.class, ()->{
            userRegister.register("id", "pw", "email");
        });

    }

    @Test
    @DisplayName("회원 가입시 암호 검사 수행함")
    void checkPassword() {
        userRegister.register("id", "pw", "email");

        then(passwordChecker).should().checkPasswordWeak(anyString());
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "email@email.com");

        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        then(emailNotifier).should().sendRegisterEmail(captor.capture());

        String realEmail = captor.getValue();
        assertEquals("email@email.com", realEmail);
    }
}
