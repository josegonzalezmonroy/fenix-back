package com.projetofinal.backend.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.dto.login.LoginRequest;
import com.projetofinal.backend.controller.dto.login.LoginResponse;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.UsuarioService;

@RestController
public class TokenResource {
    @Autowired
    private JwtEncoder jwtEncoder;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private UsuarioService usuarioService;

    @PostMapping("login")
    public ResponseEntity<Object> login(@RequestBody LoginRequest loginRequest) {
        try {
            var usuario = usuarioRepository.findByEmail(loginRequest.email());
            var login = usuarioService.login(loginRequest, passwordEncoder);
    
            if (usuario.isEmpty() || !login) {
                throw new BadCredentialsException("Usuário ou senha inválidos!");
            }

            if (!usuario.get().getAtivo()) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuário desativado.");
            }
    
            usuario.get().setUltimoLogin(Instant.now());
            usuarioRepository.save(usuario.get());
    
            var now = Instant.now();
            var expiresIn = 3600L;
    
            var scope = usuario.get().getPerfil();
    
            var claims = JwtClaimsSet.builder()
                    .issuer("mybackend")
                    .subject(usuario.get().getId().toString())
                    .issuedAt(now)
                    .expiresAt(now.plusSeconds(expiresIn))
                    .claim("scope", scope)
                    .build();
    
            var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    
            return ResponseEntity.ok(new LoginResponse(jwtValue, expiresIn));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno do servidor");
        }
    }
}
