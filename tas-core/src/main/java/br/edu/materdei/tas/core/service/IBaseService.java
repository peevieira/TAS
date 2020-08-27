/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.core.service;

import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import java.util.List;

/**
 *
 * @author pedro
 */
public interface IBaseService<T> {
    
    List<T> findAll();
    
    T findById(Integer id) throws ResourceNotFoundException;
    
    T save(T entity);
    
    void delete(Integer id) throws ResourceNotFoundException;     
    
}
