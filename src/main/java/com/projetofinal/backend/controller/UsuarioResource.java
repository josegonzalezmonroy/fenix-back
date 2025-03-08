package com.projetofinal.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.projetofinal.backend.controller.dto.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.UsuarioRequestDTO;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.exceptions.UserAlreadyDisabledException;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("usuarios")
public class UsuarioResource {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private MapperService mapperService;

    @GetMapping
    public ResponseEntity<List<Usuario>> getUsuarios() {
        List<Usuario> lista = usuarioService.getAllUsers();

        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioService.findById(id);

            return ResponseEntity.ok().body(usuario);

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("inativos")
    public ResponseEntity<List<Usuario>> getUsuariosInativos() {
        List<Usuario> lista = usuarioService.getAllInactiveUsers();

        return ResponseEntity.ok().body(lista);
    }

    @PostMapping
    public ResponseEntity<String> saveUsuario(@Valid @RequestBody UsuarioRequestDTO dto) {
        try {
            usuarioService.save(mapperService.usuarioRequestDTOToUsuario(dto));
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail já cadastrado.");
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> updateUsuario(@Valid @RequestBody UsuarioEditDTO dto, @PathVariable Long id) {
        try {

            Usuario editUsuario = mapperService.usuarioEditDTOToUsuario(dto);
            editUsuario.setId(id);

            usuarioService.update(editUsuario);

            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("E-mail já cadastrado.");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.desativarUsuario(id);
            return ResponseEntity.ok("Usuário deletado com sucesso.");
        } catch (UserAlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
