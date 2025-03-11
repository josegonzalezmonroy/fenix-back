package com.projetofinal.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.backend.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
        List<Atividade> findByAtivo(Boolean ativo);

        List<Atividade> findByUsuarios_Id(Long id);
}
