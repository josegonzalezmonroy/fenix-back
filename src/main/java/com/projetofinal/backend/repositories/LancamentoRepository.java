package com.projetofinal.backend.repositories;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetofinal.backend.entities.LancamentosHoras;

public interface LancamentoRepository extends JpaRepository<LancamentosHoras, Long> {
    List<LancamentosHoras> findByAtivo(boolean ativo);

    @Query("SELECT registro FROM LancamentosHoras registro WHERE " +
            "(:inicio < registro.dataFim AND :fim > registro.dataInicio) AND registro.id != :id")
    List<LancamentosHoras> findConflictingHoras(@Param("inicio") Instant inicio,
            @Param("fim") Instant fim,
            @Param("id") Long id);
}
