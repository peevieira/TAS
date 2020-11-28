/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.condicional.entity;

import br.edu.materdei.tas.venda.entity.ClienteEntity;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author pedro
 */

@Entity
@Table(name = "condicional")
public class CondicionalEntity {
    
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 6, nullable = false)
    private String codigo;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dtcondicional;
    
    @Temporal(TemporalType.DATE)    
    private Date dtretorno;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private ClienteEntity cliente;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<ItemCondicionalEntity> itens;

    public CondicionalEntity() {
        this.dtcondicional = new Date();
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @param codigo the codigo to set
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /**
     * @return the dtcondicional
     */
    public Date getDtcondicional() {
        return dtcondicional;
    }

    /**
     * @param dtcondicional the dtcondicional to set
     */
    public void setDtcondicional(Date dtcondicional) {
        this.dtcondicional = dtcondicional;
    }

    /**
     * @return the dtretorno
     */
    public Date getDtretorno() {
        return dtretorno;
    }

    /**
     * @param dtretorno the dtretorno to set
     */
    public void setDtretorno(Date dtretorno) {
        this.dtretorno = dtretorno;
    }

    /**
     * @return the cliente
     */
    public ClienteEntity getCliente() {
        return cliente;
    }

    /**
     * @param cliente the cliente to set
     */
    public void setCliente(ClienteEntity cliente) {
        this.cliente = cliente;
    }

    /**
     * @return the itens
     */
    public List<ItemCondicionalEntity> getItens() {
        return itens;
    }

    /**
     * @param itens the itens to set
     */
    public void setItens(List<ItemCondicionalEntity> itens) {
        this.itens = itens;
    }      
    
    
    
}
