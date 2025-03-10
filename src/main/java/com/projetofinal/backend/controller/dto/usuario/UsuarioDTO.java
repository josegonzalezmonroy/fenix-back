package com.projetofinal.backend.controller.dto.usuario;

import java.time.Instant;

import com.projetofinal.backend.entities.Models.Perfil;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private Instant dataCriacao;
    private Instant ultimoLogin;
    private boolean ativo;
    private Perfil perfil = Perfil.USUARIO;

    public UsuarioDTO(Long id, String nome, String email, Instant dataCriacao, Instant ultimoLogin, boolean ativo,
            Perfil perfil) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = dataCriacao;
        this.ultimoLogin = ultimoLogin;
        this.ativo = ativo;
        this.perfil = perfil;
    }
    public Long getId() {
        return id;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public Instant getDataCriacao() {
        return dataCriacao;
    }
    public Instant getUltimoLogin() {
        return ultimoLogin;
    }
    public boolean isAtivo() {
        return ativo;
    }
    public Perfil getPerfil() {
        return perfil;
    }
}
