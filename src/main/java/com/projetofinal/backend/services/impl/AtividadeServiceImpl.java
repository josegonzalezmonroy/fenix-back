package com.projetofinal.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.repositories.AtividadeRepository;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.AtividadeService;

import jakarta.transaction.Transactional;

@Service
public class AtividadeServiceImpl implements AtividadeService {

    @Autowired
    private AtividadeRepository atividadeRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Transactional
    @Override
    public void save(Atividade atividade, List<Long> usuariosSelecionados) {

        atividadeRepository.save(atividade);

        List<Usuario> usuarios = usuarioRepository.findAllById(usuariosSelecionados);

        for (Usuario usuario : usuarios) {
            usuario.getAtividades().add(atividade);
            atividade.getUsuarios().add(usuario);
            usuarioRepository.save(usuario);
        }
    }

}
