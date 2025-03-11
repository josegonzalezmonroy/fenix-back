package com.projetofinal.backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.ProjetoRepository;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.ProjetoService;

import jakarta.transaction.Transactional;

@Service
public class ProjetoServiceImpl implements ProjetoService {

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Projeto> getAllProjects(boolean ativo) {

        return projetoRepository.findByAtivo(ativo);
    }

    @Transactional
    @Override
    public void save(Projeto projeto, List<Long> usuariosSelecionados) {

        projetoRepository.save(projeto);

        List<Usuario> usuarios = usuarioRepository.findAllById(usuariosSelecionados);

        for (Usuario usuario : usuarios) {
            usuario.getProjetos().add(projeto);
            projeto.getUsuarios().add(usuario);
            usuarioRepository.save(usuario);
        }
    }

    @Override
    public void update(Projeto projeto) {
        Projeto projetoAtual = projetoRepository.findById(projeto.getId())
                .orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));

        projetoAtual.setNome(projeto.getNome());
        projetoAtual.setDescricao(projeto.getDescricao());
        projetoAtual.setDataInicio(projeto.getDataInicio());
        projetoAtual.setDataFim(projeto.getDataFim());
        projetoAtual.setStatus(projeto.getStatus());
        projetoAtual.setPrioridade(projeto.getPrioridade());
        projetoAtual.setUsuarioResponsavel(projeto.getUsuarioResponsavel());

        projetoRepository.save(projetoAtual);
    }

    @Override
    public void desativarProjeto(Long id) {
        Projeto projeto = projetoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));

        if (!projeto.getAtivo()) {
            throw new AlreadyDisabledException("Projeto já está desativado.");
        }

        projeto.setAtivo(false);
        projetoRepository.save(projeto);
    }

    @Override
    public Projeto findProjectById(Long id)
    {
        return projetoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));
    }

    @Override
    public List<Projeto> findProjectosByUser(Long id) {

        return projetoRepository.findByUsuarios_Id(id);
    }

}
