package com.projetofinal.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.dto.atividade.AtividadeCreateDTO;
import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.services.AtividadeService;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ValidatorService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("atividades")
public class AtividadeSource {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private ValidatorService validatorService;

    @PostMapping()
    public ResponseEntity<String> saveAtivity(@Valid @RequestBody AtividadeCreateDTO dto) {
        try {
            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Atividade atividade = mapperService.atividadeCreateDTOToAtividade(dto);

            atividadeService.save(atividade, dto.getUsuariosId());

            return ResponseEntity.status(HttpStatus.CREATED).body("Atvidade criada com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
