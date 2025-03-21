package com.projetofinal.backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.LancamentoRepository;
import com.projetofinal.backend.services.LancamentoService;
import com.projetofinal.backend.services.ValidatorService;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private ValidatorService validatorService;

    @Override
    public void save(LancamentosHoras lancamento) {

        validatorService.validateData(lancamento.getDataInicio(), lancamento.getDataFim());

        validatorService.findConflictingHoras(lancamento.getDataInicio(), lancamento.getDataFim(), null,
                lancamento.getUsuario().getId());

        lancamentoRepository.save(lancamento);
    }

    @Override
    public void update(LancamentosHoras lancamentoEdit) {

        validatorService.validateData(lancamentoEdit.getDataInicio(), lancamentoEdit.getDataFim());

        validatorService.findConflictingHoras(lancamentoEdit.getDataInicio(), lancamentoEdit.getDataFim(),
                lancamentoEdit.getId(), lancamentoEdit.getUsuario().getId());

        LancamentosHoras lancamentoAtual = lancamentoRepository.findById(lancamentoEdit.getId())
                .orElseThrow(() -> new NoSuchElementException("Lançamento não encontrado"));

        lancamentoAtual.setDescricao(lancamentoEdit.getDescricao());
        lancamentoAtual.setDataInicio(lancamentoEdit.getDataInicio());
        lancamentoAtual.setDataFim(lancamentoEdit.getDataFim());

        lancamentoRepository.save(lancamentoAtual);
    }

    @Override
    public void desativarLancamento(Long id) {

        LancamentosHoras lancamento = lancamentoRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Lançamento não encontrado"));

        if (!lancamento.getAtivo()) {
            throw new AlreadyDisabledException("Lançamento já está desativado");
        }

        lancamento.setAtivo(false);
        lancamentoRepository.save(lancamento);
    }

    @Override
    public List<LancamentosHoras> getAllLancamentos(boolean ativo) {
        return lancamentoRepository.findByAtivo(ativo);
    }

    @Override
    public List<LancamentosHoras> findLancamentosByUsuario(Long usuarioId) {
        return lancamentoRepository.findByUsuarioAndAtivo(usuarioId, true);
    }

    @Override
    public void desativarOwnLancamento(Long idLancamento, Long idUsuario) {

        LancamentosHoras lancamento = lancamentoRepository.findById(idLancamento)
                .orElseThrow(() -> new NoSuchElementException("Lançamento não encontrado"));

        if (!lancamento.getUsuario().getId().equals(idUsuario)) {
            throw new AccessDeniedException("Você não tem permissão para desativar esse lançamento");
        }

        if (!lancamento.getAtivo()) {
            throw new AlreadyDisabledException("Lançamento já está desativado");
        }

        lancamento.setAtivo(false);
        lancamentoRepository.save(lancamento);
    }
}
