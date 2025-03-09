package com.projetofinal.backend.services;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.projetofinal.backend.controller.dto.login.LoginRequest;
import com.projetofinal.backend.entities.Usuario;

public interface UsuarioService {

    public List<Usuario> getAllUsers();

    public List<Usuario> getAllInactiveUsers();

    public boolean login(LoginRequest loginRequest, PasswordEncoder passwordEncoder);

    public void save(Usuario usuario);

    public void update(Usuario usuario);

    public void desativarUsuario(Long id);

}
