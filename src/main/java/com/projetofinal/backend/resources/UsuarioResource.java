package com.projetofinal.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")////// PARA CONTROLAR AS PERMISSOES ///////
    public ResponseEntity<List<Usuario>> getUsuarios()
    {
        List<Usuario> lista = usuarioService.getAllUsers();

        return ResponseEntity.ok().body(lista);
    }
}
