package com.projetofinal.backend.controller.dto.atividade;

import java.time.Instant;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.projetofinal.backend.entities.Models.StatusAtividade;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtividadeEditDTO {
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
    private StatusAtividade status;

    @NotNull(message = "A lista de usuários é obrigatória")
    @Size(min = 1, message = "A lista de usuários deve conter pelo menos um usuário")
    @JsonProperty("usuarios_ids")
    private List<Long> usuariosId;

    public AtividadeEditDTO(@NotBlank(message = "O nome é obrigatório") String nome,
            @NotBlank(message = "A descrição é obrigatória") String descricao,
            @NotNull(message = "Data inicio obrigatória") Instant dataInicio,
            @NotNull(message = "Data fim obrigatória") Instant dataFim,
            @NotNull(message = "Status obrigatório") StatusAtividade status,
            @NotNull(message = "A lista de usuários é obrigatória") @Size(min = 1, message = "A lista de usuários deve conter pelo menos um usuário") List<Long> usuariosId) {
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.status = status;
        this.usuariosId = usuariosId;
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

    public List<Long> getUsuariosId() {
        return usuariosId;
    }
}
