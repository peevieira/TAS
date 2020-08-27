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

/**
 *
 * @author pedro
 */

@Service
public class GrupoService implements IBaseService<GrupoEntity> {

    @Autowired
    private GrupoRepository repository;    
    
    @Override
    public List<GrupoEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    public GrupoEntity findById(Integer id) throws ResourceNotFoundException {
        return this.repository.findById(id).get();
    }

    @Override
    public GrupoEntity save(GrupoEntity entity) {
        return this.repository.save(entity);
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        this.delete(id);
    }
    
}
