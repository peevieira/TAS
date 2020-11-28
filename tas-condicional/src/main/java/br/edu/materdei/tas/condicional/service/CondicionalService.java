/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.condicional.service;

import br.edu.materdei.tas.condicional.entity.CondicionalEntity;
import br.edu.materdei.tas.condicional.repository.CondicionalRepository;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.IBaseService;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author pedro
 */

@Service
public class CondicionalService implements IBaseService<CondicionalEntity>{
    
    @Autowired
    private CondicionalRepository repository;

    @Override
    @Transactional
    public List<CondicionalEntity> findAll() {
        return repository.findAll();
    }

    @Override
    @Transactional
    public CondicionalEntity findById(Integer id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(id));
    }

    @Override
    @Transactional
    public CondicionalEntity save(CondicionalEntity entity) {
        return repository.saveAndFlush(entity);
    }

    @Override
    @Transactional
    public void delete(Integer id) throws ResourceNotFoundException {
        repository.deleteById(id);
    }
    
}