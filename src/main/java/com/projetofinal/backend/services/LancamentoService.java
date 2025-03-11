package com.projetofinal.backend.services;

import java.util.List;

import com.projetofinal.backend.entities.LancamentosHoras;

public interface LancamentoService {

    public List<LancamentosHoras> getAllLancamentos(boolean ativo);

    public void save(LancamentosHoras lancamento);

    public void update(LancamentosHoras lancamento);

    public void desativarLancamento(Long id);

    public List<LancamentosHoras> findLancamentosByUsuario(Long usuarioId);

    public void desativarOwnLancamento(Long idLancamento, Long idUsuario);
}
