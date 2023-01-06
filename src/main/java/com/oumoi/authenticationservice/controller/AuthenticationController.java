package com.oumoi.authenticationservice.controller;


import com.oumoi.authenticationservice.dto.AuthenticateRequest;
import com.oumoi.authenticationservice.dto.AuthenticationResponse;
import com.oumoi.authenticationservice.dto.RegisterRequest;
import com.oumoi.authenticationservice.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){

        return ResponseEntity.ok(authService.register(request));
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticateRequest request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
