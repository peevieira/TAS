/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.core.exception;

/**
 *
 * @author pedro
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(Integer resourceId) {        
        super(resourceId != null ? resourceId.toString() : null);        
    } 
    
}
