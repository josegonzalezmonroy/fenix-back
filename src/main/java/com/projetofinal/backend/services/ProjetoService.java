package com.projetofinal.backend.services;

import java.util.List;

import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;

public interface ProjetoService {

    public List<Projeto> getAllProjects(boolean ativo);
    
    public void save(Projeto projeto, List<Long> usuariosSelecionados);

    public void update(Projeto projeto);

    public void desativarProjeto(Long id);

    public Projeto findProjectById(Long id);

    public List<Projeto> findProjectosByUser(Long id);

    public List<Usuario> getUsersByProjectId(Long projectId);
}
