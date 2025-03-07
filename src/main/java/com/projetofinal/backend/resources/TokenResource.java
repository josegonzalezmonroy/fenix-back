package com.projetofinal.backend.resources;

import java.time.Instant;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.resources.dto.LoginRequest;
import com.projetofinal.backend.resources.dto.LoginResponse;

@RestController
public class TokenResource {
    private final JwtEncoder jwtEncoder;
    private final UsuarioRepository usuarioRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TokenResource(JwtEncoder jwtEncoder, UsuarioRepository usuarioRepository,
            BCryptPasswordEncoder passwordEncoder) {
        this.jwtEncoder = jwtEncoder;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        var usuario = usuarioRepository.findByEmail(loginRequest.email());

        if (usuario.isEmpty() || !usuario.get().isLoginCorrect(loginRequest, passwordEncoder)) {
            throw new BadCredentialsException("Usuário ou senha inválidos!");
        }

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
    }
}
