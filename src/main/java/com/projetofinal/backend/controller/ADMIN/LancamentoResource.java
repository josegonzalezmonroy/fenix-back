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

import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoDTO;
import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoEditDTO;
import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.LancamentoRepository;
import com.projetofinal.backend.services.LancamentoService;
import com.projetofinal.backend.services.MapperService;

import jakarta.validation.Valid;

@RestController
@PreAuthorize("hasAuthority('SCOPE_ADMIN')")
@RequestMapping("lancamentos")
public class LancamentoResource {

    @Autowired
    private MapperService mapperService;

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public ResponseEntity<List<LancamentoDTO>> getAllLancamentos() {
        List<LancamentoDTO> listaDTO = lancamentoService.getAllLancamentos(true).stream().map(
                mapperService::lancamentosHorasToLancamentoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("inativos")
    public ResponseEntity<List<LancamentoDTO>> getAllInactiveLancamentos() {
        List<LancamentoDTO> listaDTO = lancamentoService.getAllLancamentos(false).stream().map(
                mapperService::lancamentosHorasToLancamentoDTO).collect(Collectors.toList());

        return ResponseEntity.ok().body(listaDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<Object> getAtividadeById(@PathVariable Long id) {
        try {
            LancamentosHoras lancamentos = lancamentoRepository.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Lançamento não encontrado"));

            return ResponseEntity.ok().body(mapperService.lancamentosHorasToLancamentoDTO(lancamentos));
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity<String> saveLancamento(@Valid @RequestBody LancamentoCreateDTO dto) {

        try {

            LancamentosHoras lancamento = mapperService.lancamentoCreateDTOToLancamentos(dto);

            lancamentoService.save(lancamento);

            return ResponseEntity.status(HttpStatus.CREATED).body("Lancamento criado com sucesso!");

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(e.getMessage());
        }
    }

    @PatchMapping("{id}")
    public ResponseEntity<String> updateLancamento(@Valid @RequestBody LancamentoEditDTO dto, @PathVariable Long id) {
        try {

            LancamentosHoras lancamentoEdit = mapperService.lancamentoEditDTOToLancamentos(dto);
            lancamentoEdit.setId(id);

            lancamentoService.update(lancamentoEdit);

            return ResponseEntity.status(HttpStatus.OK).body("Lancamento modificado com sucesso!");

        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteLancamento(@PathVariable Long id) {
        try {

            lancamentoService.desativarLancamento(id);
            return ResponseEntity.ok("Lançamento desativado com sucesso!");
            
        } catch (AlreadyDisabledException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}
