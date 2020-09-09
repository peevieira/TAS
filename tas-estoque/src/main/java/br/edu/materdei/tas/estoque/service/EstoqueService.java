/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.estoque.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import br.edu.materdei.tas.estoque.entity.EstoqueEntity;
import br.edu.materdei.tas.estoque.repository.EstoqueRepository;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */

@Service
public class EstoqueService implements IBaseService<EstoqueEntity> {

    @Autowired
    private EstoqueRepository repository;
    
    @Override
    @Transactional
    public List<EstoqueEntity> findAll() {
        return this.repository.findAll();
    }

    @Override
    @Transactional
    public EstoqueEntity findById(Integer id) throws ResourceNotFoundException {
        return this.repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public EstoqueEntity save(EstoqueEntity entity) {
        return this.repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        this.repository.deleteById(id);
    }
    
}
