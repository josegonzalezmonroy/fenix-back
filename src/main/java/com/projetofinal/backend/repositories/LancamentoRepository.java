package com.projetofinal.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.backend.entities.LancamentosHoras;

public interface LancamentoRepository extends JpaRepository<LancamentosHoras, Long>{

}
