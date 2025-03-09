package com.projetofinal.backend.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.controller.dto.UsuarioEditDTO;
import com.projetofinal.backend.controller.dto.ProjetoCreateDTO;
import com.projetofinal.backend.controller.dto.UsuarioCreateDTO;
import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.entities.Usuario;
import com.projetofinal.backend.repositories.UsuarioRepository;
import com.projetofinal.backend.services.MapperService;

@Service
public class MapperServiceImpl implements MapperService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

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

        Usuario usuarioResponsavel = usuarioRepository.findById(dto.getIdUsuarioResponsavel())
                .orElseThrow(() -> new RuntimeException("Usuário responsável não encontrado"));

        projeto.setUsuarioResponsavel(usuarioResponsavel);

        return projeto;
    }

}
