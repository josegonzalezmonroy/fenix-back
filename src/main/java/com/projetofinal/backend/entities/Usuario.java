package com.projetofinal.backend.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.projetofinal.backend.entities.Models.Perfil;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @CreationTimestamp
    @Column(nullable = false, updatable = false, name = "data_criacao")
    private Instant dataCriacao;

    @Column(name = "ultimo_login")
    private Instant ultimoLogin;

    @Column(nullable = false)
    private Boolean ativo = true;
    
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Perfil perfil = Perfil.USUARIO;

    @OneToMany(mappedBy = "usuarioResponsavel")
    @JsonBackReference
    private List<Projeto> projetosResponsavel;

    @ManyToMany
    @JoinTable(name = "usuarios_projetos", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_projeto"))
    private List<Projeto> projetos;

    @ManyToMany
    @JoinTable(name = "usuarios_atividades", joinColumns = @JoinColumn(name = "id_usuario"), inverseJoinColumns = @JoinColumn(name = "id_atividade"))
    private List<Atividade> atividades;

    @OneToMany(mappedBy = "usuario")
    private List<LancamentosHoras> lancamentosHoras;

    public Usuario() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Instant getUltimoLogin() {
        return ultimoLogin;
    }

    public void setUltimoLogin(Instant ultimoLogin) {
        this.ultimoLogin = ultimoLogin;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Projeto> getProjetosResponsavel() {
        return projetosResponsavel;
    }

    public void setProjetosResponsavel(List<Projeto> projetosResponsavel) {
        this.projetosResponsavel = projetosResponsavel;
    }

    public List<Projeto> getProjetos() {
        return projetos;
    }

    public void setProjetos(List<Projeto> projetos) {
        this.projetos = projetos;
    }

    public List<Atividade> getAtividades() {
        return atividades;
    }

    public void setAtividades(List<Atividade> atividades) {
        this.atividades = atividades;
    }

    public List<LancamentosHoras> getLancamentosHoras() {
        return lancamentosHoras;
    }

    public void setLancamentosHoras(List<LancamentosHoras> lancamentosHoras) {
        this.lancamentosHoras = lancamentosHoras;
    }
}
