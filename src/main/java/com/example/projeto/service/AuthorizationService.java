package com.example.projeto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.projeto.dtos.RegisterDTO;
import com.example.projeto.models.UserModel;
import com.example.projeto.repository.UserRepository;

@Service
public class AuthorizationService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email);
    }

    public ResponseEntity<Object> register(RegisterDTO registerDto) {
        
       if(userRepository.findByEmail(registerDto.email()) != null){
            return ResponseEntity.badRequest().build();
       }
       else {
         String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

         UserModel userModel = new UserModel(registerDto.nome(), registerDto.email(), encryptedPassword, registerDto.role());
        
         this.userRepository.save(userModel);

         return ResponseEntity.ok().build();

       }
    }
      
    
}
