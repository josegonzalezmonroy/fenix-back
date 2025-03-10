package com.projetofinal.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.dto.projeto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.dto.projeto.ProjetoEditDTO;
import com.projetofinal.backend.controller.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.exceptions.UserAlreadyDisabledException;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ProjetoService;
import com.projetofinal.backend.services.ValidatorService;
import com.projetofinal.backend.repositories.ProjetoRepository;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("projetos")
public class ProjetoResource {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private MapperService mapperService;

    @Autowired
    private ProjetoRepository projetoRepository;

    @Autowired
    private ValidatorService validatorService;

    @GetMapping
    public ResponseEntity<List<ProjetoDTO>> getAllProjects() {

        List<ProjetoDTO> listaDTO = projetoService.getAllProjects().stream()
                .map(mapperService::projetoToProjetoSimplificadoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getProjetoById(@PathVariable Long id) {
        try {
            Projeto projeto = projetoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));

            return ResponseEntity.ok().body(mapperService.projetoToProjetoSimplificadoDTO(projeto));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> saveProjeto(@Valid @RequestBody ProjetoCreateDTO dto) {
        try {

            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Projeto projeto = mapperService.projetoCreateDTOToProjeto(dto);

            projetoService.save(projeto, dto.getUsuarios());

            return ResponseEntity.status(HttpStatus.CREATED).body("Projeto criado com sucesso!");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> updateProjeto(@Valid @RequestBody ProjetoEditDTO dto, @PathVariable Long id) {
        try {
            Projeto editProjeto = mapperService.projetoEditDTOToProjeto(dto);
            editProjeto.setId(id);

            projetoService.update(editProjeto);

            return ResponseEntity.status(HttpStatus.OK).body("Projeto modificado com sucesso!");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteProjeto(@PathVariable Long id) {
        try {
            projetoService.desativarProjeto(id);
            return ResponseEntity.ok("Projeto deletado com sucesso!");
        } catch (UserAlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
