package com.projetofinal.backend.controller.dto.lancamentos;

import java.time.Instant;

import com.projetofinal.backend.controller.dto.atividade.AtividadeDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioSimplificadoDTO;

public class LancamentoDTO {
    private Long id;
    private AtividadeDTO atividade;
    private UsuarioSimplificadoDTO usuario;
    private String descricao;
    private Instant dataInicio;
    private Instant dataFim;
    private Instant dataRegistro;
    private boolean ativo;

    public LancamentoDTO(Long id, AtividadeDTO atividade, UsuarioSimplificadoDTO usuario, String descricao,
            Instant dataInicio, Instant dataFim, Instant dataRegistro, boolean ativo) {
        this.id = id;
        this.atividade = atividade;
        this.usuario = usuario;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.dataRegistro = dataRegistro;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public AtividadeDTO getAtividade() {
        return atividade;
    }

    public UsuarioSimplificadoDTO getUsuario() {
        return usuario;
    }

    public String getDescricao() {
        return descricao;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public Instant getDataRegistro() {
        return dataRegistro;
    }

    public boolean getAtivo() {
        return ativo;
    }
}
