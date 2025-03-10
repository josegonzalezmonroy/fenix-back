package com.projetofinal.backend.services;

import java.util.List;

import com.projetofinal.backend.entities.Atividade;

public interface AtividadeService {
    public void save(Atividade atividade, List<Long> usuariosSelecionados);
}
