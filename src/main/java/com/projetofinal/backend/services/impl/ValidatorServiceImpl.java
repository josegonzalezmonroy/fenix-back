package com.projetofinal.backend.services.impl;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.exceptions.InvalidDateException;
import com.projetofinal.backend.repositories.LancamentoRepository;
import com.projetofinal.backend.services.ValidatorService;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Override
    public void validateData(Instant dataInicio, Instant dataFim) {
        if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
            throw new InvalidDateException("A data de início não pode ser posterior à data de fim");
        }
    }

    @Override
    public void findConflictingHoras(Instant inicio, Instant fim, Long id) {
        List<LancamentosHoras> conflitos = lancamentoRepository.findConflictingHoras(
                inicio, fim, id);

        if (!conflitos.isEmpty()) {
            throw new InvalidDateException("Há um conflito de horas com outros registros.");
        }
    }
}
