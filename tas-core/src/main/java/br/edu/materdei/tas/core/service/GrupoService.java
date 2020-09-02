/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.repository.GrupoRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pedro
 */
@Service
public class GrupoService implements IBaseService<GrupoEntity> {

    @Autowired
    private GrupoRepository repository;

    @Override
    @Transactional
    public List<GrupoEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public GrupoEntity findById(Integer id) throws ResourceNotFoundException {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public GrupoEntity save(GrupoEntity entity) {
        return this.repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        this.repository.deleteById(id);
    }

}
