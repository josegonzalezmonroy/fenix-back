package com.projetofinal.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.controller.dto.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.UsuarioCreateDTO;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.services.MapperService;

@Service
public class MapperServiceImpl implements MapperService{

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Usuario usuarioRequestDTOToUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
            
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail()); 
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return usuario;
    }

    @Override 
    public Usuario usuarioEditDTOToUsuario(UsuarioEditDTO dto)
    {
        Usuario usuarioEdit = new Usuario();

        usuarioEdit.setNome(dto.getNome());
        usuarioEdit.setEmail(dto.getEmail()); 
        usuarioEdit.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuarioEdit.setPerfil(dto.getPerfil());

        return usuarioEdit; 
    }

}
