package com.projetofinal.backend.controller.ADMIN;

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

import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoEditDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
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
        List<ProjetoDTO> listaDTO = projetoService.getAllProjects(true).stream()
                .map(mapperService::projetoToProjetoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("inativos")
    public ResponseEntity<List<ProjetoDTO>> getAllInactiveProjects() {
        List<ProjetoDTO> listaDTO = projetoService.getAllProjects(false).stream()
                .map(mapperService::projetoToProjetoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getProjetoById(@PathVariable Long id) {
        try {
            Projeto projeto = projetoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Projeto não encontrado"));

            return ResponseEntity.ok().body(mapperService.projetoToProjetoDTO(projeto));

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<ResponseMessage> saveProjeto(@Valid @RequestBody ProjetoCreateDTO dto) {
        try {

            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Projeto projeto = mapperService.projetoCreateDTOToProjeto(dto);

            projetoService.save(projeto, dto.getUsuarios());

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Projeto criado com sucesso!"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));    
        }   
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseMessage> updateProjeto(@Valid @RequestBody ProjetoEditDTO dto, @PathVariable Long id) {
        try {

            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Projeto editProjeto = mapperService.projetoEditDTOToProjeto(dto);
            editProjeto.setId(id);

            projetoService.update(editProjeto);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Projeto modificado com sucesso!"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));    
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteProjeto(@PathVariable Long id) {
        try {
            projetoService.desativarProjeto(id);
            return ResponseEntity.ok(new ResponseMessage("Projeto desativado com sucesso!"));
        } catch (AlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }

    @GetMapping("{projectId}/usuarios")
    public ResponseEntity<List<UsuarioSimplificadoDTO>> getUsersByProjectId(@PathVariable Long projectId) {
        List<UsuarioSimplificadoDTO> usuariosDTO = projetoService.getUsersByProjectId(projectId).stream().map(
            mapperService::usuarioToUsuarioSimplificadoDTO
        ).collect(Collectors.toList());
        return ResponseEntity.ok(usuariosDTO);
    }

    @GetMapping("usuario/{id}")
    public ResponseEntity<List<ProjetoDTO>> getProjectsByUsuario(@PathVariable Long id){
        List<ProjetoDTO> projetosDTO = projetoService.findProjectosByUser(id).stream().map(mapperService::projetoToProjetoDTO).collect(Collectors.toList());

        return ResponseEntity.ok(projetosDTO);
    }
}
