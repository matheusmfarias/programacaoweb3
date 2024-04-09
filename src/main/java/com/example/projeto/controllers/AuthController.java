package com.example.projeto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.projeto.dtos.AuthenticationDTO;
import com.example.projeto.dtos.LoginRespotaDTO;
import com.example.projeto.dtos.RegisterDTO;
import com.example.projeto.models.UserModel;
import com.example.projeto.service.AuthorizationService;
import com.example.projeto.service.TokenService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@RestController
@AllArgsConstructor
@NoArgsConstructor
public class AuthController {
   
    private AuthenticationManager authenticationManager;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private AuthorizationService authorizationService;



    @Autowired
    private TokenService tokenService;

    @PostMapping("/public/login")
    public ResponseEntity<Object> login(@RequestBody @Valid AuthenticationDTO authetinticationDto){
        authenticationManager = context.getBean(AuthenticationManager.class);
        
        var usernamePassword = new UsernamePasswordAuthenticationToken(authetinticationDto.email(), authetinticationDto.password());

        var auth = this.authenticationManager.authenticate(usernamePassword);

        var token = tokenService.generateToken((UserModel) auth.getPrincipal());

        return ResponseEntity.ok(new LoginRespotaDTO(token));
    }

    @PostMapping("/public/register")
    public ResponseEntity<Object> register (@RequestBody RegisterDTO registerDto){
        return authorizationService.register(registerDto);
    }
    
}