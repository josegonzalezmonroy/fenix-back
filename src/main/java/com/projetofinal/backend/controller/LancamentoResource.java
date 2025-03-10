package com.projetofinal.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.dto.lancamentos.LancamentoCreateDTO;
import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.repositories.LancamentoRepository;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ValidatorService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("lancamentos")
public class LancamentoResource {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ValidatorService validatorService;

    @PostMapping
    public ResponseEntity<String> saveLancamento(@Valid @RequestBody LancamentoCreateDTO dto) {
        try {
            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            LancamentosHoras lancamento = mapperService.lancamentoCreateDTOToLancamentos(dto);

            lancamentoRepository.save(lancamento);

            return ResponseEntity.status(HttpStatus.CREATED).body("Lancamento criado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }
}
