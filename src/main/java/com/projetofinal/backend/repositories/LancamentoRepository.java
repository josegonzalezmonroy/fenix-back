package com.projetofinal.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.backend.entities.LancamentosHoras;

public interface LancamentoRepository extends JpaRepository<LancamentosHoras, Long>{
    List<LancamentosHoras> findByAtivo(boolean ativo);
}
