/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.condicional.repository;

import br.edu.materdei.tas.condicional.entity.CondicionalEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author pedro
 */

@Repository
public interface CondicionalRepository extends JpaRepository<CondicionalEntity, Integer> {
    
}
