package animal.crud.machadolabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import animal.crud.machadolabtest.dto.SignupRequest;
import animal.crud.machadolabtest.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/signup")
public class SignUpController {
    private final AuthService authService;

    @Autowired
    public SignUpController(AuthService authService){
        this.authService=authService;
    }

    @PostMapping    
    public ResponseEntity<String> signupUser(@RequestBody SignupRequest signupRequest){
        boolean isUserCreated = authService.createUser(signupRequest);
        if(isUserCreated){
            return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create user");
        }
    }
}
