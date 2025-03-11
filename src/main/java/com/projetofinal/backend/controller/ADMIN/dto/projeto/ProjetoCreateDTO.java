package com.projetofinal.backend.controller.ADMIN.dto.projeto;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projetofinal.backend.entities.Models.Prioridade;
import com.projetofinal.backend.entities.Models.StatusProjeto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProjetoCreateDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @JsonProperty("data_inicio")
    @NotNull(message = "Data inicio obrigatória")
    private Instant dataInicio;

    @JsonProperty("data_fim")
    @NotNull(message = "Data fim obrigatória")
    private Instant dataFim;

    @NotNull(message = "Status obrigatório")
    @Enumerated(EnumType.STRING)
    private StatusProjeto status;

    @NotNull(message = "Prioridade obrigatória")
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @JsonProperty("id_usuario_responsavel")
    @NotNull(message = "Usuário responsável obrigatório")
    private Long idUsuarioResponsavel;

    @NotEmpty(message = "A lista deve conter pelo menos um usuário")
    private List<Long> usuarios;

    public ProjetoCreateDTO(String nome, String descricao, Instant dataInicio, Instant dataFim, StatusProjeto status,
            Prioridade prioridade, Long idUsuarioResponsavel, List<Long> usuarios) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.prioridade = prioridade;
        this.idUsuarioResponsavel = idUsuarioResponsavel;
        this.usuarios = usuarios;
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

    public StatusProjeto getStatus() {
        return status;
    }

    public Prioridade getPrioridade() {
        return prioridade;
    }

    public Long getIdUsuarioResponsavel() {
        return idUsuarioResponsavel;
    }

    public List<Long> getUsuarios() {
        return usuarios;
    }
}
