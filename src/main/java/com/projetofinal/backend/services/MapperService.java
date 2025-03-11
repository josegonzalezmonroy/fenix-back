package com.projetofinal.backend.services;

import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeDTO;
import com.projetofinal.backend.controller.ADMIN.dto.atividade.AtividadeEditDTO;
import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoDTO;
import com.projetofinal.backend.controller.ADMIN.dto.lancamentos.LancamentoEditDTO;
import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoDTO;
import com.projetofinal.backend.controller.ADMIN.dto.projeto.ProjetoEditDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioCreateDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioEditDTO;
import com.projetofinal.backend.controller.ADMIN.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.controller.USER.dto.ProfileCreateLancamentoDTO;
import com.projetofinal.backend.controller.USER.dto.ProfileEditDTO;
import com.projetofinal.backend.entities.Atividade;
import com.projetofinal.backend.entities.LancamentosHoras;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;

public interface MapperService {

    public Usuario usuarioCreateDTOToUsuario(UsuarioCreateDTO dto);

    public Usuario usuarioEditDTOToUsuario(UsuarioEditDTO dto);

    public Projeto projetoCreateDTOToProjeto(ProjetoCreateDTO dto);

    public Projeto projetoEditDTOToProjeto(ProjetoEditDTO dto);

    public UsuarioSimplificadoDTO usuarioToUsuarioSimplificadoDTO(Usuario usuario);

    public ProjetoDTO projetoToProjetoDTO(Projeto projeto);

    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario);

    public Atividade atividadeCreateDTOToAtividade(AtividadeCreateDTO dto);

    public Atividade atividadeEditDTOtoAtividade(AtividadeEditDTO dto);

    public AtividadeDTO atividadeToAtividadeDTO(Atividade atividade);

    public LancamentosHoras lancamentoCreateDTOToLancamentos(LancamentoCreateDTO dto); 

    public LancamentosHoras lancamentoEditDTOToLancamentos(LancamentoEditDTO dto);

    public LancamentoDTO lancamentosHorasToLancamentoDTO(LancamentosHoras lancamentos);

    public Usuario profileEditDTOtoUsuario(ProfileEditDTO dto, Long id);

    public LancamentosHoras profileCreateLancamentoDTOToLancamentos(ProfileCreateLancamentoDTO dto, Long id);
}
