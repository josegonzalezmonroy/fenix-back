package com.projetofinal.backend.services;

import java.time.Instant;

public interface ValidatorService {
    public void validateData(Instant dataInicio, Instant dataFim);
}
