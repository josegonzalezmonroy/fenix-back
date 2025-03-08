package com.projetofinal.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.services.ProjetoService;

@RestController
@RequestMapping("/projetos")
public class ProjetoResource {

    @Autowired
    private ProjetoService projetoService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity<List<Projeto>> getUsuarios()
    {
        List<Projeto> lista = projetoService.getAllProjects();

        return ResponseEntity.ok().body(lista);
    }
}
