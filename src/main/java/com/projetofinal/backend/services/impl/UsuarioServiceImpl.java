package com.projetofinal.backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.controller.dto.login.LoginRequest;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean login(LoginRequest loginRequest, PasswordEncoder passwordEncoder) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(loginRequest.email());

        if (usuarioOpt.isEmpty()) {
            return false;
        }

        return passwordEncoder.matches(loginRequest.senha(), usuarioOpt.get().getSenha());
    }

    @Override
    public List<Usuario> getAllUsers() {
        List<Usuario> usuariosAtivos = usuarioRepository.findByAtivo(true);
        return usuariosAtivos;
    }

    @Override
    public List<Usuario> getAllInactiveUsers() {
        List<Usuario> usuariosInativos = usuarioRepository.findByAtivo(false);
        return usuariosInativos;
    }

    @Override
    public void save(Usuario usuario) {
        usuarioRepository.save(usuario);
    }

    @Override
    public void update(Usuario usuario) {
        Usuario usuarioAtual = usuarioRepository.findById(usuario.getId())
        .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

        usuarioAtual.setNome(usuario.getNome());
        usuarioAtual.setEmail(usuario.getEmail());
        usuarioAtual.setSenha(usuario.getSenha());
        usuarioAtual.setPerfil(usuario.getPerfil());

        usuarioRepository.save(usuarioAtual);
    }

    @Override
    public void desativarUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado."));

        if (!usuario.getAtivo()) {
            throw new AlreadyDisabledException("Usuário já está desativado.");
        }

        usuario.setAtivo(false);
        usuarioRepository.save(usuario);
    }

    @Override
    public Usuario findUserById(Long id)
    {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Usuário não encontrado."));

        return usuario;
    }

    @Override
    public List<Usuario> findAllUsersById(List<Long> idList)
    {
        List<Usuario> lista = usuarioRepository.findAllById(idList);

        return lista;
    }
}
