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
                        "(:inicio < registro.dataFim AND :fim > registro.dataInicio) " +
                        "AND (:id IS NULL OR registro.id != :id) " +
                        "AND registro.usuario.id = :usuarioId " +
                        "AND registro.ativo = true")
        List<LancamentosHoras> findConflictingHoras(@Param("inicio") Instant inicio,
                        @Param("fim") Instant fim,
                        @Param("id") Long id,
                        @Param("usuarioId") Long usuarioId);

        @Query("SELECT registro FROM LancamentosHoras registro WHERE registro.usuario.id = :usuarioId AND registro.ativo = :ativo")
        List<LancamentosHoras> findByUsuarioAndAtivo(@Param("usuarioId") Long usuarioId, @Param("ativo") boolean ativo);
}
