package com.projetofinal.backend.controller.USER;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeDTO;
import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoDTO;
import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.controller.USER.dto.ProfileCreateLancamentoDTO;
import com.projetofinal.backend.controller.USER.dto.ProfileEditDTO;
import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.services.AtividadeService;
import com.projetofinal.backend.services.LancamentoService;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ProjetoService;
import com.projetofinal.backend.services.UsuarioService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_USUARIO')")
@RequestMapping("user")
public class UserResource {

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private MapperService mapperService;

    @GetMapping("projetos")
    public ResponseEntity<List<ProjetoDTO>> getAllProjects(@AuthenticationPrincipal Jwt jwt) {

        Long idUser = Long.parseLong(jwt.getSubject());

        List<ProjetoDTO> listaDTO = projetoService.findProjectosByUser(idUser).stream()
                .map(mapperService::projetoToProjetoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("atividades")
    public ResponseEntity<List<AtividadeDTO>> getAllActivities(@AuthenticationPrincipal Jwt jwt) {

        Long id = Long.parseLong(jwt.getSubject());

        List<AtividadeDTO> listaDTO = atividadeService.findActivityByUser(id).stream()
                .map(mapperService::atividadeToAtividadeDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("lancamentos")
    public ResponseEntity<List<LancamentoDTO>> getAllLancamentos(@AuthenticationPrincipal Jwt jwt) {

        Long id = Long.parseLong(jwt.getSubject());

        List<LancamentoDTO> listaDTO = lancamentoService.findLancamentosByUsuario(id).stream()
                .map(mapperService::lancamentosHorasToLancamentoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @DeleteMapping("lancamentos/{id}")
    public ResponseEntity<String> deleteLancamento(@PathVariable Long id, @AuthenticationPrincipal Jwt jwt) {
        Long idUsuario = Long.parseLong(jwt.getSubject());
        try {

            lancamentoService.desativarOwnLancamento(id, idUsuario);
            return ResponseEntity.ok("Lançamento desativado com sucesso!");

        } catch (AlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<String> saveLancamento(@Valid @RequestBody ProfileCreateLancamentoDTO dto,
            @AuthenticationPrincipal Jwt jwt) {

        Long id = Long.parseLong(jwt.getSubject());

        LancamentosHoras lancamento = mapperService.profileCreateLancamentoDTOToLancamentos(dto, id);

        lancamentoService.save(lancamento);

        return ResponseEntity.status(HttpStatus.CREATED).body("Lancamento criado com sucesso!");
    }

    @PatchMapping
    public ResponseEntity<String> updateUsuario(@Valid @RequestBody ProfileEditDTO dto,
            @AuthenticationPrincipal Jwt jwt) {

        Long id = Long.parseLong(jwt.getSubject());

        Usuario editUsuario = mapperService.profileEditDTOtoUsuario(dto, id);

        usuarioService.update(editUsuario);

        return ResponseEntity.status(HttpStatus.OK).body("Dados alterados com sucesso!");
    }

}