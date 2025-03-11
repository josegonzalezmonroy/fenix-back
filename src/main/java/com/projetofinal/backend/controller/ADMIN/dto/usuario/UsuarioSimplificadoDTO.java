package com.projetofinal.backend.controller.ADMIN.dto.usuario;

import java.time.Instant;

public class UsuarioSimplificadoDTO {

    private Long id;
    private String nome;
    private String email;
    private boolean ativo;
    private Instant ultimoLogin;

    public UsuarioSimplificadoDTO(Long id, String nome, String email, boolean ativo, Instant ultimoLogin) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.ultimoLogin = ultimoLogin;
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

    public boolean getAtivo() {
        return ativo;
    }

    public Instant getUltimoLogin(){
        return ultimoLogin;
    }
}
