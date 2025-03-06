package com.projetofinal.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projetofinal.backend.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
