package ch07.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserRegisterTest {
    private UserRegister userRegister;
    private StubWeakPasswordChecker passwordChecker;
    private UserRepository userRepository;
    private SpyEmailNotifier spyEmailNotifier;

    @BeforeEach
    void setUp() {
        passwordChecker = new StubWeakPasswordChecker();
        userRepository = new MemoryUserRepository();
        spyEmailNotifier = new SpyEmailNotifier();

        userRegister = new UserRegister(passwordChecker, userRepository, spyEmailNotifier);
    }

    @Test
    @DisplayName("약한 암호면 가입 실패")
    void weakPassword() {
        passwordChecker.setWeak(true);

        assertThrows(WeakPasswordException.class, () -> {
            userRegister.register("id", "pw", "email");
        });
    }

    @Test
    @DisplayName("이미 같은 ID가 존재하면 가입 실패")
    void dupIdExists() {
        userRepository.save(new User("id", "pw1", "email@naver.com"));

        assertThrows(DupIdException.class, () -> {
            userRegister.register("id", "pw1", "email.naver.com");
        });
    }

    @Test
    @DisplayName("같은 ID가 없으면 가입 성공함")
    void noDupIdRegisterSuccess() {
        userRegister.register("id", "pw", "test@naver.com");

        User saved = userRepository.findById("id");

        assertEquals("id", saved.getUserId());
        assertEquals("test@naver.com", saved.getEmail());
    }

    @Test
    @DisplayName("가입하면 메일을 전송함")
    void whenRegisterThenSendMail() {
        userRegister.register("id", "pw", "test@naver.com");

        assertTrue(spyEmailNotifier.isCalled());
        assertEquals("test@naver.com", spyEmailNotifier.getEmail());
    }
}
