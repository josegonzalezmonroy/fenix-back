package com.projetofinal.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAllUsers() {
        return usuarioRepository.findAll();
    }
}
