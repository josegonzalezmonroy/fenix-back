package com.projetofinal.backend.services;

import java.util.List;

import com.projetofinal.backend.entities.Atividade;

public interface AtividadeService {

    public List<Atividade> getAllActivities(boolean ativo);
    
    public void save(Atividade atividade, List<Long> usuariosSelecionados);

    public void update(Atividade atividade);

    public void desativarAtividade(Long id);
}
