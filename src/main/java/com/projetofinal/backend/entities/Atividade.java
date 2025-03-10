package com.projetofinal.backend.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

import com.projetofinal.backend.entities.Models.StatusAtividade;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "atividade")
public class Atividade implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "data_inicio", nullable = false)
    private Instant dataInicio;

    @Column(name = "data_fim", nullable = false)
    private Instant dataFim;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusAtividade status;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private Instant dataCriacao = Instant.now();

    @Column(nullable = false)
    private boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "id_projeto", nullable = false)
    private Projeto projeto;

    @OneToMany(mappedBy = "atividade")
    private List<LancamentosHoras> lancamentosHoras;

    @ManyToMany(mappedBy = "atividades")
    private List<Usuario> usuarios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Instant getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Instant dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Instant getDataFim() {
        return dataFim;
    }

    public void setDataFim(Instant dataFim) {
        this.dataFim = dataFim;
    }

    public StatusAtividade getStatus() {
        return status;
    }

    public void setStatus(StatusAtividade status) {
        this.status = status;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<LancamentosHoras> getLancamentosHoras() {
        return lancamentosHoras;
    }

    public void setLancamentosHoras(List<LancamentosHoras> lancamentosHoras) {
        this.lancamentosHoras = lancamentosHoras;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }
}
