/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.compra.service;

import br.edu.materdei.tas.compra.entity.FornecedorEntity;
import br.edu.materdei.tas.compra.repository.FornecedorRepository;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author pedro
 */

@Service
public class FornecedorService implements IBaseService<FornecedorEntity> {
    
    @Autowired
    private FornecedorRepository repository;

    @Override
    @Transactional
    public List<FornecedorEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public FornecedorEntity findById(Integer id) throws ResourceNotFoundException {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public FornecedorEntity save(FornecedorEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }   
       
}
