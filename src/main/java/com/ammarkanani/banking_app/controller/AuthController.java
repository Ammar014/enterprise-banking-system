package com.ammarkanani.banking_app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.ammarkanani.banking_app.dto.request.LoginRequestDTO;
import com.ammarkanani.banking_app.dto.response.ApiResponse;
import com.ammarkanani.banking_app.dto.response.LoginResponseDTO;
import com.ammarkanani.banking_app.security.JwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    public AuthController(
            AuthenticationManager authenticationManager,
            JwtService jwtService) {

        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<LoginResponseDTO>> login(
            @Valid @RequestBody LoginRequestDTO requestDTO) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                requestDTO.getEmail(),
                                requestDTO.getPassword()));

        String token =
                jwtService.generateToken(
                        (org.springframework.security.core.userdetails.UserDetails)
                                authentication.getPrincipal());

        LoginResponseDTO responseDTO =
                new LoginResponseDTO(token);

        ApiResponse<LoginResponseDTO> response =
                new ApiResponse<>(
                        true,
                        "Login successful.",
                        responseDTO);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(response);
    }
}