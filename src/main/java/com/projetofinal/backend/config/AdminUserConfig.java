package com.projetofinal.backend.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.entities.Models.Perfil;
import com.projetofinal.backend.repositories.UsuarioRepository;

import jakarta.transaction.Transactional;

@Configuration
public class AdminUserConfig implements CommandLineRunner {

    private UsuarioRepository usuarioRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public AdminUserConfig(UsuarioRepository usuarioRepository, BCryptPasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        var usuarioAdmin = usuarioRepository.findByEmail("admin@admin.com");

        usuarioAdmin.ifPresentOrElse(
                user -> {
                    System.out.println("Admin já existe");
                },
                () -> {
                    var usuario = new Usuario();
                    usuario.setNome("admin");
                    usuario.setEmail("admin@admin.com");
                    usuario.setSenha(passwordEncoder.encode("123456"));
                    usuario.setPerfil(Perfil.ADMIN);

                    usuarioRepository.save(usuario);
                }

        );
    }
}
