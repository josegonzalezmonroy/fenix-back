package com.projetofinal.backend.controller.ADMIN;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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

import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioEditDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.UsuarioRepository;
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
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MapperService mapperService;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getAllUsers() {
        List<UsuarioDTO> listaDTO = usuarioService.getAllUsers(true).stream()
                .map(mapperService::usuarioToUsuarioDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("inativos")
    public ResponseEntity<List<UsuarioDTO>> getAllInactiveUsers() {
        List<UsuarioDTO> listaDTO = usuarioService.getAllUsers(false).stream()
                .map(mapperService::usuarioToUsuarioDTO).collect(Collectors.toList());
            
        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getUsuarioById(@PathVariable Long id) {
        try {
            Usuario usuario = usuarioRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Usuário não encontrado"));

            return ResponseEntity.ok().body(mapperService.usuarioToUsuarioDTO(usuario));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @GetMapping("atividades/{id}")
    public ResponseEntity<List<UsuarioSimplificadoDTO>> findByAtividades_Id(@PathVariable Long id) {
        List<UsuarioSimplificadoDTO> listaDTO = usuarioRepository.findByAtividades_Id(id).stream()
                .map(mapperService::usuarioToUsuarioSimplificadoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> saveUsuario(@Valid @RequestBody UsuarioCreateDTO dto) {
        try {
            usuarioService.save(mapperService.usuarioCreateDTOToUsuario(dto));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Usuário criado com sucesso!"));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage("E-mail já cadastrado"));
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseMessage> updateUsuario(@Valid @RequestBody UsuarioEditDTO dto, @PathVariable Long id) {
        try {

            Usuario editUsuario = mapperService.usuarioEditDTOToUsuario(dto);
            editUsuario.setId(id);

            usuarioService.update(editUsuario);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Usuário modificado com sucesso!"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
            .body(new ResponseMessage("E-mail já cadastrado"));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteUsuario(@PathVariable Long id) {
        try {
            usuarioService.desativarUsuario(id);
            return ResponseEntity.ok(new ResponseMessage("Usuário desativado com sucesso!"));
        } catch (AlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }
}
