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

import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeDTO;
import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeEditDTO;
import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.AtividadeRepository;
import com.projetofinal.backend.services.AtividadeService;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ValidatorService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("atividades")
public class AtividadeResource {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private AtividadeService atividadeService;

    @Autowired
    private AtividadeRepository atividadeRepository;

    @Autowired
    private ValidatorService validatorService;

    @GetMapping
    public ResponseEntity<List<AtividadeDTO>> getAllActivities() {
        List<AtividadeDTO> listaDTO = atividadeService.getAllActivities(true).stream()
                .map(mapperService::atividadeToAtividadeDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("inativas")
    public ResponseEntity<List<AtividadeDTO>> getAllInactiveActitivies() {
        List<AtividadeDTO> listaDTO = atividadeService.getAllActivities(false).stream()
                .map(mapperService::atividadeToAtividadeDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getAtividadeById(@PathVariable Long id) {
        try {
            Atividade atividade = atividadeRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Atividade não encontrada"));

            return ResponseEntity.ok().body(mapperService.atividadeToAtividadeDTO(atividade));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<ResponseMessage> saveActivity(@Valid @RequestBody AtividadeCreateDTO dto) {
        try {
            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Atividade atividade = mapperService.atividadeCreateDTOToAtividade(dto);

            atividadeService.save(atividade, dto.getUsuariosId());

            return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Atividade criada com sucesso!"));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ResponseMessage(e.getMessage()));
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<ResponseMessage> updateAtividade(@Valid @RequestBody AtividadeEditDTO dto, @PathVariable Long id) {
        try {

            validatorService.validateData(dto.getDataInicio(), dto.getDataFim());

            Atividade editAtiviadde = mapperService.atividadeEditDTOtoAtividade(dto);
            editAtiviadde.setId(id);

            atividadeService.update(editAtiviadde);

            return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Atividade modificada com sucesso!"));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseMessage> deleteProjeto(@PathVariable Long id) {
        try {
            atividadeService.desativarAtividade(id);
            return ResponseEntity.ok(new ResponseMessage("Atividade desativada com sucesso!"));
        } catch (AlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(e.getMessage()));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage(e.getMessage()));
        }
    }

    @GetMapping("/projeto/{projetoId}/usuario/{usuarioId}")
    public ResponseEntity<List<AtividadeDTO>> getAtividadesByProjetoAndUsuario(
            @PathVariable Long projetoId, 
            @PathVariable Long usuarioId) {
        List<AtividadeDTO> atividades = atividadeService.getAtividadesByProjetoAndUsuario(projetoId, usuarioId).stream().map(mapperService::atividadeToAtividadeDTO).collect(Collectors.toList());
        
        return ResponseEntity.ok(atividades);
    }

    @GetMapping("/projeto/{projetoId}")
    public ResponseEntity<List<AtividadeDTO>> getAtividadesByProjeto(@PathVariable Long projetoId){
        List<AtividadeDTO> atividades = atividadeService.getAtividadesByProjeto(projetoId).stream().map(mapperService::atividadeToAtividadeDTO).collect(Collectors.toList());
        
        return ResponseEntity.ok(atividades);
    }
}
