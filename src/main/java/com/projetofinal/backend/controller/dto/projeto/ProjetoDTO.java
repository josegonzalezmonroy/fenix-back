package com.projetofinal.backend.controller.dto.projeto;

import java.time.Instant;

import com.projetofinal.backend.controller.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.entities.Models.Prioridade;
import com.projetofinal.backend.entities.Models.StatusProjeto;

public class ProjetoDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Instant dataInicio;
    private Instant dataFim;
    private StatusProjeto status;
    private UsuarioSimplificadoDTO usuarioResponsavel;
    private Instant dataCriacao;
    private Prioridade prioridade;
    private boolean ativo;

    public ProjetoDTO(Long id, String nome, String descricao, Instant dataInicio, Instant dataFim, StatusProjeto status,
            UsuarioSimplificadoDTO usuarioResponsavel, Instant dataCriacao, Prioridade prioridade, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.usuarioResponsavel = usuarioResponsavel;
        this.dataCriacao = dataCriacao;
        this.prioridade = prioridade;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public StatusProjeto getStatus()
    {
        return status;
    }

    public String getDescricao() {
        return descricao;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public UsuarioSimplificadoDTO getUsuarioResponsavel() {
        return usuarioResponsavel;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }
}
