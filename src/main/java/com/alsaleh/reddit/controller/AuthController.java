package com.alsaleh.reddit.controller;

import com.alsaleh.reddit.dto.AuthenticationResponse;
import com.alsaleh.reddit.dto.LoginRequest;
import com.alsaleh.reddit.dto.RegisterRequest;
import com.alsaleh.reddit.services.AuthService;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody RegisterRequest registerRequest) {
        authService.signup(registerRequest);
        return new ResponseEntity<>("User Registration Successful", HttpStatus.OK);
    }

    @GetMapping("accountVerification/{token}")
    public ResponseEntity<String> verifyAccount(@PathVariable String token) {
        authService.verifyAccount(token);
        return new ResponseEntity<>("Account Activated Successfully", HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthenticationResponse response = authService.login(loginRequest);
        return ResponseEntity.ok(response);
    }

}
