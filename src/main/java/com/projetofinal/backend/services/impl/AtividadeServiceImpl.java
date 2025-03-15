package com.projetofinal.backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
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

    @Override
    public List<Atividade> getAllActivities(boolean ativo)
    {
        return atividadeRepository.findByAtivo(ativo);
    }

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

    @Override
    public void update(Atividade atividade) {
        Atividade atividadeAtual = atividadeRepository.findById(atividade.getId())
                .orElseThrow(() -> new NoSuchElementException("Atividade não encontrada"));

        atividadeAtual.setNome(atividade.getNome());
        atividadeAtual.setDescricao(atividade.getDescricao());
        atividadeAtual.setDataInicio(atividade.getDataInicio());
        atividadeAtual.setDataFim(atividade.getDataFim());
        atividadeAtual.setStatus(atividade.getStatus());

        atividadeRepository.save(atividadeAtual);
    }

    @Override
    public void desativarAtividade(Long id)
    {
        Atividade atividade = atividadeRepository.findById(id).orElseThrow(()->new NoSuchElementException("Atividade não encontrada"));

        if (!atividade.getAtivo())
        {
            throw new AlreadyDisabledException("Atividade já está desativada");
        }

        atividade.setAtivo(false);
        atividadeRepository.save(atividade);
    }

    @Override
    public Atividade findActivityById(Long id) {
        return atividadeRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Atividade não encontrada"));
    }

    @Override
    public List<Atividade> findActivityByUser(Long id) {
        return atividadeRepository.findByUsuarios_Id(id);
    }

    @Override
    public List<Atividade> getAtividadesByProjetoAndUsuario(Long projetoId, Long usuarioId) {
        return atividadeRepository.findByProjetoIdAndUsuarioId(projetoId, usuarioId);
    }

    @Override
    public List<Atividade> findAllActivitiesByUserAndProject(Long userId, Long projectId) {
        return atividadeRepository.findByProjetoIdAndUsuarioId(userId,projectId);
    }

}
