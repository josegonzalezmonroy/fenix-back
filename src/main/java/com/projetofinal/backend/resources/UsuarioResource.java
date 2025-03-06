package com.projetofinal.backend.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.services.UsuarioService;

@RestController
@RequestMapping("/usuario")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios()
    {
        List<Usuario> lista = usuarioService.getAllUsers();

        return ResponseEntity.ok().body(lista);
    }
}
