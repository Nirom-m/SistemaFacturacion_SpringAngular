package com.bolsadeideas.springboot.backend.apirest.spbackendapirest.controller;

import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.auth.jwt.TokenInfo;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.dto.AuthenticationRequest;
import com.bolsadeideas.springboot.backend.apirest.spbackendapirest.models.services.JwtUtilServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = {"http://localhost:4200", "*"})
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService usuarioDetailsService;

    @Autowired
    private JwtUtilServiceImpl jwtUtilServiceImpl;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @PostMapping("/login")
    public ResponseEntity<?>login (AuthenticationRequest authenticationRequest){
        logger.info("Autenticando al usuario {}", authenticationRequest.getUsername());

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));

        final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
                authenticationRequest.getUsername());

        final String jwt = jwtUtilServiceImpl.generateToken(userDetails);

        return ResponseEntity.ok(new TokenInfo(jwt));
    }

}
