package com.projetofinal.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.AtividadeService;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.ProjetoService;
import com.projetofinal.backend.services.UsuarioService;

@Service
public class MapperServiceImpl implements MapperService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ProjetoService projetoService;

    @Autowired
    private AtividadeService atividadeService;

    @Override
    public Usuario usuarioCreateDTOToUsuario(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();

        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));

        return usuario;
    }

    @Override
    public Usuario usuarioEditDTOToUsuario(UsuarioEditDTO dto) {
        Usuario usuarioEdit = new Usuario();

        usuarioEdit.setNome(dto.getNome());
        usuarioEdit.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isEmpty()) {
            usuarioEdit.setSenha(passwordEncoder.encode(dto.getSenha()));
        }

        usuarioEdit.setPerfil(dto.getPerfil());

        return usuarioEdit;
    }

    @Override
    public UsuarioSimplificadoDTO usuarioToUsuarioSimplificadoDTO(Usuario usuario) {
        return new UsuarioSimplificadoDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getAtivo(),
                usuario.getUltimoLogin());
    }

    @Override
    public UsuarioDTO usuarioToUsuarioDTO(Usuario usuario) {
        return new UsuarioDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getDataCriacao(),
                usuario.getUltimoLogin(), usuario.getAtivo(), usuario.getPerfil());
    }

    @Override
    public Projeto projetoCreateDTOToProjeto(ProjetoCreateDTO dto) {
        Projeto projeto = new Projeto();

        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        projeto.setStatus(dto.getStatus());
        projeto.setPrioridade(dto.getPrioridade());

        Usuario usuarioResponsavel = usuarioService.findUserById(dto.getIdUsuarioResponsavel());

        projeto.setUsuarioResponsavel(usuarioResponsavel);

        List<Usuario> usuarios = usuarioRepository.findAllById(dto.getUsuarios());

        projeto.setUsuarios(usuarios);

        return projeto;
    }

    @Override
    public Projeto projetoEditDTOToProjeto(ProjetoEditDTO dto) {
        Projeto projeto = new Projeto();

        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());
        projeto.setDataInicio(dto.getDataInicio());
        projeto.setDataFim(dto.getDataFim());
        projeto.setStatus(dto.getStatus());
        projeto.setPrioridade(dto.getPrioridade());

        Usuario usuarioResponsavel = usuarioService.findUserById(dto.getIdUsuarioResponsavel());

        projeto.setUsuarioResponsavel(usuarioResponsavel);

        return projeto;
    }

    @Override
    public ProjetoDTO projetoToProjetoDTO(Projeto projeto) {

        return new ProjetoDTO(projeto.getId(), projeto.getNome(), projeto.getDescricao(), projeto.getDataInicio(),
                projeto.getDataFim(), projeto.getStatus(),
                usuarioToUsuarioSimplificadoDTO(projeto.getUsuarioResponsavel()), projeto.getDataCriacao(),
                projeto.getPrioridade(), projeto.getAtivo());
    }

    @Override
    public Atividade atividadeCreateDTOToAtividade(AtividadeCreateDTO dto) {

        Atividade atividade = new Atividade();

        Projeto projeto = projetoService.findProjectById(dto.getProjeto());
        List<Usuario> usuarios = usuarioService.findAllUsersById(dto.getUsuariosId());

        atividade.setNome(dto.getNome());
        atividade.setDescricao(dto.getDescricao());
        atividade.setDataInicio(dto.getDataInicio());
        atividade.setDataFim(dto.getDataFim());
        atividade.setStatus(dto.getStatus());
        atividade.setProjeto(projeto);
        atividade.setUsuarios(usuarios);

        return atividade;
    }

    @Override
    public Atividade atividadeEditDTOtoAtividade(AtividadeEditDTO dto) {
        Atividade atividade = new Atividade();

        atividade.setNome(dto.getNome());
        atividade.setDescricao(dto.getDescricao());
        atividade.setDataInicio(dto.getDataInicio());
        atividade.setDataFim(dto.getDataFim());
        atividade.setStatus(dto.getStatus());

        return atividade;
    }

    @Override
    public AtividadeDTO atividadeToAtividadeDTO(Atividade atividade) {
        ProjetoDTO projetoDTO = projetoToProjetoDTO(projetoService.findProjectById(atividade.getProjeto().getId()));

        return new AtividadeDTO(atividade.getId(), atividade.getNome(), atividade.getDescricao(),
                atividade.getDataInicio(), atividade.getDataFim(), atividade.getStatus(), atividade.getDataCriacao(),
                atividade.getAtivo(), projetoDTO);
    }

    @Override
    public LancamentosHoras lancamentoCreateDTOToLancamentos(LancamentoCreateDTO dto) {
        LancamentosHoras lancamento = new LancamentosHoras();

        Atividade atividade = atividadeService.findActivityById(dto.getAtividade());

        Usuario usuario = usuarioService.findUserById(dto.getUsuario());

        lancamento.setDescricao(dto.getDescricao());
        lancamento.setDataInicio(dto.getDataInicio());
        lancamento.setDataFim(dto.getDataFim());
        lancamento.setAtividade(atividade);
        lancamento.setUsuario(usuario);

        return lancamento;
    }

    @Override
    public LancamentosHoras lancamentoEditDTOToLancamentos(LancamentoEditDTO dto) {
        LancamentosHoras lancamento = new LancamentosHoras();

        Atividade atividade = atividadeService.findActivityById(dto.getAtividade());

        Usuario usuario = usuarioService.findUserById(dto.getUsuario());

        lancamento.setDescricao(dto.getDescricao());
        lancamento.setDataInicio(dto.getDataInicio());
        lancamento.setDataFim(dto.getDataFim());
        lancamento.setAtividade(atividade);
        lancamento.setUsuario(usuario);

        return lancamento;
    }

    @Override
    public LancamentoDTO lancamentosHorasToLancamentoDTO(LancamentosHoras lancamentos) {

        UsuarioSimplificadoDTO usuario = usuarioToUsuarioSimplificadoDTO(
                usuarioService.findUserById(lancamentos.getUsuario().getId()));

        AtividadeDTO atividade = atividadeToAtividadeDTO(
                atividadeService.findActivityById(lancamentos.getAtividade().getId()));

        return new LancamentoDTO(lancamentos.getId(), atividade, usuario, lancamentos.getDescricao(),
                lancamentos.getDataInicio(), lancamentos.getDataFim(), lancamentos.getDataFim(),
                lancamentos.getAtivo());
    }

    @Override
    public Usuario profileEditDTOtoUsuario(ProfileEditDTO dto, Long id) {
        Usuario usuarioEdit = usuarioService.findUserById(id);

        usuarioEdit.setNome(dto.getNome());
        usuarioEdit.setSenha(passwordEncoder.encode(dto.getSenha()));

        return usuarioEdit;
    }

    @Override
    public LancamentosHoras profileCreateLancamentoDTOToLancamentos(ProfileCreateLancamentoDTO dto, Long id) {
        LancamentosHoras lancamento = new LancamentosHoras();

        Usuario usuario = usuarioService.findUserById(id);

        Atividade atividade = atividadeService.findActivityById(dto.getAtividade());

        lancamento.setAtividade(atividade);
        lancamento.setDescricao(dto.getDescricao());
        lancamento.setDataInicio(dto.getDataInicio());
        lancamento.setDataFim(dto.getDataFim());

        lancamento.setUsuario(usuario);

        return lancamento;
    }
}
