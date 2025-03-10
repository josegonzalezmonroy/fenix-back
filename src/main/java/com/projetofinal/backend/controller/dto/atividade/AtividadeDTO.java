package com.projetofinal.backend.controller.dto.atividade;

import java.time.Instant;
import java.time.LocalDate;

import com.projetofinal.backend.controller.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.entities.Models.StatusAtividade;

public class AtividadeDTO {

    private Long id;
    private String nome;
    private String descricao;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private StatusAtividade status;
    private Instant dataCriacao;
    private boolean ativo = true;
    private UsuarioSimplificadoDTO usuarioResponsavel;
    private ProjetoDTO projeto;
    
    public AtividadeDTO(Long id, String nome, String descricao, LocalDate dataInicio, LocalDate dataFim,
            StatusAtividade status, Instant dataCriacao, boolean ativo, UsuarioSimplificadoDTO usuarioResponsavel,
            ProjetoDTO projeto) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.ativo = ativo;
        this.usuarioResponsavel = usuarioResponsavel;
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
    public LocalDate getDataInicio() {
        return dataInicio;
    }
    public LocalDate getDataFim() {
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
    public UsuarioSimplificadoDTO getUsuarioResponsavel() {
        return usuarioResponsavel;
    }
    public ProjetoDTO getProjeto() {
        return projeto;
    }
}
