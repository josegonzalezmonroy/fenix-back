package com.projetofinal.backend.services.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.exceptions.AlreadyDisabledException;
import com.projetofinal.backend.repositories.LancamentoRepository;
import com.projetofinal.backend.services.LancamentoService;

@Service
public class LancamentoServiceImpl implements LancamentoService{

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public void update(LancamentosHoras lancamentoEdit) {
        
        LancamentosHoras lancamentoAtual = lancamentoRepository.findById(lancamentoEdit.getId())
                        .orElseThrow(() -> new NoSuchElementException("Lançamento não encontrado"));

        lancamentoAtual.setDescricao(lancamentoEdit.getDescricao());
        lancamentoAtual.setDataInicio(lancamentoEdit.getDataInicio());
        lancamentoAtual.setDataFim(lancamentoEdit.getDataFim());

        lancamentoRepository.save(lancamentoAtual);
    }

    @Override
    public void desativarLancamento(Long id) {

        LancamentosHoras lancamento = lancamentoRepository.findById(id).orElseThrow(()->new NoSuchElementException("Lançamento não encontrado"));

        if (!lancamento.getAtivo())
        {
            throw new AlreadyDisabledException("Lançamento já está desativado");
        }

        lancamento.setAtivo(false);
        lancamentoRepository.save(lancamento);        
    }

    @Override
    public List<LancamentosHoras> getAllLancamentos(boolean ativo) {
        return lancamentoRepository.findByAtivo(ativo);
    }
    
}
