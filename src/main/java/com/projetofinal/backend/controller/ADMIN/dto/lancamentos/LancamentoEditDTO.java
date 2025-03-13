package com.projetofinal.backend.controller.ADMIN.dto.lancamentos;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class LancamentoEditDTO {

    @JsonProperty("id_atividade")
    @NotNull(message = "Atividade obrigatória")
    private Long atividade;

    @JsonProperty("id_usuario")
    @NotNull(message = "Usuário obrigatório")
    private Long usuario;

    @NotBlank(message = "A descrição é obrigatória")
    private String descricao;

    @JsonProperty("data_inicio")
    @NotNull(message = "Data inicio obrigatória")
    private Instant dataInicio;

    @JsonProperty("data_fim")
    @NotNull(message = "Data fim obrigatória")
    private Instant dataFim;

    public LancamentoEditDTO(Long usuario, Long atividade, String descricao, Instant dataInicio, Instant dataFim) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.usuario = usuario;
        this.atividade = atividade;

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

    public Long getUsuario()
    {
        return usuario;
    }

    public Long getAtividade()
    {
        return atividade;
    }
}
