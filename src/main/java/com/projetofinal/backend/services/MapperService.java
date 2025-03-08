package com.projetofinal.backend.services;

import com.projetofinal.backend.controller.dto.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.UsuarioRequestDTO;
import com.projetofinal.backend.entities.Usuario;

public interface MapperService {

    public Usuario usuarioRequestDTOToUsuario(UsuarioRequestDTO dto);

    public Usuario usuarioEditDTOToUsuario(UsuarioEditDTO dto);
}
