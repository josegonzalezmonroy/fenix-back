package com.projetofinal.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projetofinal.backend.entities.Atividade;

public interface AtividadeRepository extends JpaRepository<Atividade, Long> {
        List<Atividade> findByAtivo(Boolean ativo);

        List<Atividade> findByUsuarios_Id(Long id);

           @Query("SELECT a FROM Atividade a " +
           "JOIN a.usuarios u " +
           "WHERE a.projeto.id = :projetoId AND u.id = :usuarioId")
    List<Atividade> findByProjetoIdAndUsuarioId(@Param("projetoId") Long projetoId, 
                                                @Param("usuarioId") Long usuarioId);
}
