package com.projetofinal.backend.controller;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.dto.atividade.AtividadeCreateDTO;
import com.projetofinal.backend.controller.dto.atividade.AtividadeEditDTO;
import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.services.AtividadeService;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ValidatorService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("atividades")
public class AtividadeResource {

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

    @PatchMapping("{id}")
    public ResponseEntity<String> updateAtividade(@Valid @RequestBody AtividadeEditDTO dto, @PathVariable Long id) {
        try {

            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Atividade editAtiviadde = mapperService.atividadeEditDTOtoAtividade(dto);
            editAtiviadde.setId(id);

            atividadeService.update(editAtiviadde);

            return ResponseEntity.status(HttpStatus.OK).body("Atividade modificada com sucesso!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProjeto(@PathVariable Long id) {
        try {
            atividadeService.desativarAtividade(id);
            return ResponseEntity.ok("Atividade desativada com sucesso!");
        } catch (AlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
