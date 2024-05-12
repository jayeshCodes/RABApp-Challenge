package animal.crud.machadolabtest.service;

import animal.crud.machadolabtest.dto.SignupRequest;

public interface AuthService {
    boolean createUser(SignupRequest signupRequest);

}
