package com.projetofinal.backend.services;

import com.projetofinal.backend.controller.dto.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.controller.dto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.dto.ProjetoEditDTO;
import com.projetofinal.backend.controller.dto.UsuarioCreateDTO;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;

public interface MapperService {

    public Usuario usuarioCreateDTOToUsuario(UsuarioCreateDTO dto);

    public Usuario usuarioEditDTOToUsuario(UsuarioEditDTO dto);

    public Projeto projetoCreateDTOToProjeto(ProjetoCreateDTO dto);

    public Projeto projetoEditDTOToProjeto(ProjetoEditDTO dto);

    public UsuarioSimplificadoDTO usuarioToUsuarioSimplificadoDTO(Usuario usuario);
}
