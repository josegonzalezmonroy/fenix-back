package com.projetofinal.backend.services;

import java.util.List;

import com.projetofinal.backend.entities.Projeto;

public interface ProjetoService {
    public List<Projeto> getAllProjects();

    public void save(Projeto projeto, List<Long> usuariosSelecionados);

    public void update(Projeto projeto);

}
