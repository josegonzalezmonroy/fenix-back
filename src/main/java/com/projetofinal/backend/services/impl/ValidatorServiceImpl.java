package com.projetofinal.backend.services.impl;

import java.time.Instant;

import org.springframework.stereotype.Service;

import com.projetofinal.backend.exceptions.InvalidDateException;
import com.projetofinal.backend.services.ValidatorService;

@Service
public class ValidatorServiceImpl implements ValidatorService {

    @Override
    public void validateData(Instant dataInicio, Instant dataFim) {
        if (dataInicio != null && dataFim != null && dataInicio.isAfter(dataFim)) {
            throw new InvalidDateException("A data de início não pode ser posterior à data de fim");
        }
    }

}
