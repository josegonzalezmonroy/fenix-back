package com.projetofinal.backend.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetofinal.backend.entities.Projeto;
import com.projetofinal.backend.repositories.ProjetoRepository;
import com.projetofinal.backend.services.ProjetoService;

@Service
public class ProjetoServiceImpl implements ProjetoService{

    @Autowired
    private ProjetoRepository projetoRepository;
    
    @Override
    public List<Projeto> getAllProjects() {

        return projetoRepository.findAll();
    }

    @Override
    public void save(Projeto projeto) {
        projetoRepository.save(projeto);
    }
}
