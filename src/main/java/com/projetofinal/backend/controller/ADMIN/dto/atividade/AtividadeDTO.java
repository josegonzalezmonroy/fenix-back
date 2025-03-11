package com.projetofinal.backend.controller.ADMIN.dto.atividade;

import java.time.Instant;

import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.entities.Models.StatusAtividade;

public class AtividadeDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Instant dataInicio;
    private Instant dataFim;
    private StatusAtividade status;
    private Instant dataCriacao;
    private boolean ativo = true;
    private ProjetoDTO projeto;
    
    public AtividadeDTO(Long id, String nome, String descricao, Instant dataInicio, Instant dataFim,
            StatusAtividade status, Instant dataCriacao, boolean ativo,
            ProjetoDTO projeto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.projeto = projeto;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
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
    public StatusAtividade getStatus() {
        return status;
    }
    public Instant getDataCriacao() {
        return dataCriacao;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public ProjetoDTO getProjeto() {
        return projeto;
    }
}
