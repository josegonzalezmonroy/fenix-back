package com.projetofinal.backend.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.controller.dto.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.projeto.ProjetoSimplificadoDTO;
import com.projetofinal.backend.controller.dto.usuario.UsuarioSimplificadoDTO;
import com.projetofinal.backend.controller.dto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.dto.ProjetoEditDTO;
import com.projetofinal.backend.controller.dto.UsuarioCreateDTO;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.MapperService;
import com.projetofinal.backend.services.UsuarioService;

@Service
public class MapperServiceImpl implements MapperService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioService usuarioService;

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
        usuarioEdit.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuarioEdit.setPerfil(dto.getPerfil());

        return usuarioEdit;
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
    public Projeto projetoEditDTOToProjeto(ProjetoEditDTO dto)
    {
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
    public UsuarioSimplificadoDTO usuarioToUsuarioSimplificadoDTO(Usuario usuario)
    {
        List<ProjetoSimplificadoDTO> projetosDTO = usuario.getProjetos().stream().map(projeto->projetoToProjetoSimplificadoDTO(projeto)).collect(Collectors.toList());

        UsuarioSimplificadoDTO dto = new UsuarioSimplificadoDTO(usuario.getId(), usuario.getNome(), usuario.getEmail(), usuario.getAtivo(), projetosDTO);

        return dto;
    }

    public ProjetoSimplificadoDTO projetoToProjetoSimplificadoDTO(Projeto projeto)
    {
        ProjetoSimplificadoDTO dto = new ProjetoSimplificadoDTO(projeto.getId(), projeto.getNome(), projeto.getAtivo());

        return dto;
    }
}
