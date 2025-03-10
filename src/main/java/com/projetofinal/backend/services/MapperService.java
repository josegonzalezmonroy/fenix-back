package com.projetofinal.backend.services;

import com.projetofinal.backend.controller.dto.atividade.AtividadeCreateDTO;
import com.projetofinal.backend.controller.dto.atividade.AtividadeEditDTO;
import com.projetofinal.backend.controller.dto.projeto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.dto.projeto.ProjetoEditDTO;
import com.projetofinal.backend.controller.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioCreateDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;

public interface MapperService {

    public Usuario usuarioCreateDTOToUsuario(UsuarioCreateDTO dto);

    public Usuario usuarioEditDTOToUsuario(UsuarioEditDTO dto);

    public Projeto projetoCreateDTOToProjeto(ProjetoCreateDTO dto);

    public Projeto projetoEditDTOToProjeto(ProjetoEditDTO dto);

    public UsuarioSimplificadoDTO usuarioToUsuarioSimplificadoDTO(Usuario usuario);

    public ProjetoDTO projetoToProjetoSimplificadoDTO(Projeto projeto);

    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    public Atividade atividadeCreateDTOToAtividade(AtividadeCreateDTO dto);

    public Atividade atividadeEditDTOtoAtividade(AtividadeEditDTO dto);
}
