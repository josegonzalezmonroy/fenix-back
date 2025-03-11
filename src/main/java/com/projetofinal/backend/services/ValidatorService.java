package com.projetofinal.backend.services;

import java.time.Instant;

public interface ValidatorService {
    public void validateData(Instant dataInicio, Instant dataFim);

    public void findConflictingHoras(Instant inicio, Instant fim, Long id, Long isUsuario);
}
