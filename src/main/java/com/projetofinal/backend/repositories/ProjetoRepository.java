package com.projetofinal.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.backend.entities.Projeto;


public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

        List<Projeto> findByAtivo(Boolean ativo);

        List<Projeto> findByUsuarios_Id(Long idUsuario);
}
