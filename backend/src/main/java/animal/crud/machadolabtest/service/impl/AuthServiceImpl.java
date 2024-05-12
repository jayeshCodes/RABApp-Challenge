package animal.crud.machadolabtest.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import animal.crud.machadolabtest.dto.SignupRequest;
import animal.crud.machadolabtest.entity.AppUser;
import animal.crud.machadolabtest.mapper.SignUpMapper;
import animal.crud.machadolabtest.repository.AppUserRepository;
import animal.crud.machadolabtest.service.AuthService;
import lombok.RequiredArgsConstructor;

@Service
public class AuthServiceImpl implements AuthService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthServiceImpl(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder){
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean createUser(SignupRequest signupRequest) {
        // check if user already exists
        if (appUserRepository.existsByEmail(signupRequest.getEmail()))
            return false;

        SignUpMapper signUpMapper = new SignUpMapper();

        AppUser user = signUpMapper.mapToAppUser(
            signupRequest
        );

        // Hash password before saving
        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);

        appUserRepository.save(user);

        return true;
    }
}
