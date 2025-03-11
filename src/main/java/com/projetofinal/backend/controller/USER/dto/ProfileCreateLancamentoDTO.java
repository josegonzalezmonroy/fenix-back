package com.projetofinal.backend.controller.USER.dto;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProfileCreateLancamentoDTO {
    @JsonProperty("id_atividade")
    @NotNull(message = "Atividade obrigatória")
    private Long atividade;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @JsonProperty("data_inicio")
    @NotNull(message = "Data inicio obrigatória")
    private Instant dataInicio;

    @JsonProperty("data_fim")
    @NotNull(message = "Data fim obrigatória")
    private Instant dataFim;

    public ProfileCreateLancamentoDTO(Long atividade, String descricao, Instant dataInicio, Instant dataFim) {
        this.atividade = atividade;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public Long getAtividade() {
        return atividade;
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
}
