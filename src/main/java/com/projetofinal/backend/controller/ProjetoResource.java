package com.projetofinal.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.dto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.dto.ProjetoEditDTO;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ProjetoService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("projetos")
public class ProjetoResource {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private MapperService mapperService;

    @GetMapping
    public ResponseEntity<List<Projeto>> getUsuarios() {
        List<Projeto> lista = projetoService.getAllProjects();

        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<String> saveProjeto(@Valid @RequestBody ProjetoCreateDTO dto) {
        try {
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
}
