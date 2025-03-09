package com.projetofinal.backend.controller.dto.usuario;

import java.util.List;

import com.projetofinal.backend.controller.dto.projeto.ProjetoSimplificadoDTO;

public class UsuarioSimplificadoDTO {

    private Long id;
    private String nome;
    private String email;
    private Boolean ativo = true;
    private List<ProjetoSimplificadoDTO> projetos;

    public UsuarioSimplificadoDTO(Long id, String nome, String email, Boolean ativo,
            List<ProjetoSimplificadoDTO> projetos) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
        this.projetos = projetos;
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

    public Boolean getAtivo() {
        return ativo;
    }

    public List<ProjetoSimplificadoDTO> getProjetos() {
        return projetos;
    }
}
