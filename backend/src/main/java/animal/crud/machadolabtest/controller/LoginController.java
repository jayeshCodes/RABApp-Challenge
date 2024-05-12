package animal.crud.machadolabtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import animal.crud.machadolabtest.dto.LoginRequest;
import animal.crud.machadolabtest.dto.LoginResponse;
import animal.crud.machadolabtest.service.jwt.AppUserServiceImpl;
import animal.crud.machadolabtest.utils.JwtUtil;
import org.springframework.web.bind.annotation.PostMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final AppUserServiceImpl appUserServiceImpl;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;
    private final ObjectMapper objectMapper; // ObjectMapper for JSON serialization

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, AppUserServiceImpl appUserServiceImpl,
            JwtUtil jwtUtil, PasswordEncoder passwordEncoder, ObjectMapper objectMapper) {
        this.authenticationManager = authenticationManager;
        this.appUserServiceImpl = appUserServiceImpl;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
        this.objectMapper = objectMapper;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        // Hash the password before authentication
        String hashedPassword = passwordEncoder.encode(loginRequest.getPassword());

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UserDetails userDetails;
        try {
            userDetails = appUserServiceImpl.loadUserByUsername(loginRequest.getEmail());
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername());

        // Create a map to hold the JWT token
        Map<String, String> jwtMap = new HashMap<>();
        jwtMap.put("jwt", jwt);

        try {
            // Serialize the map to JSON
            String jsonResponse = objectMapper.writeValueAsString(jwtMap);
            // Return the JSON response
            return ResponseEntity.ok(jsonResponse);
        } catch (JsonProcessingException e) {
            // Handle JSON serialization error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}