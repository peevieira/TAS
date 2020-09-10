/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.server.controller;

import br.edu.materdei.tas.core.entity.GrupoEntity;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.GrupoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author pedro
 */

@RestController
public class GrupoController {
    
    @Autowired
    private GrupoService service;
    
    @GetMapping("grupos")
    public ResponseEntity<List<GrupoEntity>> findAll() {
        List<GrupoEntity> grupos = service.findAll();
        
        return new ResponseEntity<List<GrupoEntity>>(grupos, HttpStatus.OK);
    }
    
    @GetMapping("grupo/{id}")
    public ResponseEntity<GrupoEntity> findById(@PathVariable("id") Integer id) throws ResourceNotFoundException {
       GrupoEntity grupo = service.findById(id);
        
        return new ResponseEntity<GrupoEntity>(grupo, HttpStatus.OK); 
    }
    
}
