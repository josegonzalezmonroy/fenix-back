package com.projetofinal.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.backend.entities.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
