package com.oumoi.authenticationservice.service;


import com.oumoi.authenticationservice.config.JWTService;
import com.oumoi.authenticationservice.dto.AuthenticateRequest;
import com.oumoi.authenticationservice.dto.AuthenticationResponse;
import com.oumoi.authenticationservice.dto.RegisterRequest;
import com.oumoi.authenticationservice.entity.Role;
import com.oumoi.authenticationservice.entity.User;
import com.oumoi.authenticationservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private JWTService jwtService;


    private AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken  = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build()  ;
    }

    public AuthenticationResponse authenticate(AuthenticateRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        var user   = userRepository.findByEmail(request.getEmail()).orElseThrow();

        var jwtToken  = jwtService.generateToken(user);

        return  AuthenticationResponse.builder()
                .token(jwtToken)
                .build()  ;
    }
}
