package com.projetofinal.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;


public interface ProjetoRepository extends JpaRepository<Projeto, Long> {

        List<Projeto> findByAtivo(Boolean ativo);

        List<Projeto> findByUsuarios_Id(Long idUsuario);

        @Query("SELECT usuario FROM Usuario usuario JOIN usuario.projetos projeto WHERE projeto.id = :projectId")
        List<Usuario> findUsuariosByProjetoId(Long projectId);
}
