package ch08;

public class LoginService {
    private AuthService authService = new AuthService();
    private String authKey = "someKey";
    private CustomerRepository customerRepository;

    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    public LoginService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public LoginResult login(String id, String pw) {
        int resp = authService.authenticate(id, pw);
        if (resp == -1) {
            return LoginResult.badAuthKey();
        }
        if (resp == 1) {
            Customer c = customerRepository.findOne(id);
            return LoginResult.authenticated(c);
        } else {
            return LoginResult.fail(resp);
        }
    }
}
