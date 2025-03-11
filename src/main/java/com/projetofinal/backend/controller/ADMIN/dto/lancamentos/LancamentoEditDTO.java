package com.projetofinal.backend.controller.ADMIN.dto.lancamentos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LancamentoEditDTO {

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @JsonProperty("data_inicio")
    @NotNull(message = "Data inicio obrigatória")
    private Instant dataInicio;

    @JsonProperty("data_fim")
    @NotNull(message = "Data fim obrigatória")
    private Instant dataFim;

    public LancamentoEditDTO(String descricao, Instant dataInicio, Instant dataFim) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
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
