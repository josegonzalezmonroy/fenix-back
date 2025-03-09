package com.projetofinal.backend.controller.dto.projeto;

public class ProjetoSimplificadoDTO {
    private Long id;
    private String nome;
    private boolean ativo;

    public ProjetoSimplificadoDTO(Long id, String nome, boolean ativo) {
        this.id = id;
        this.nome = nome;
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

}
