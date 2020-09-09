/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.venda.entity;

import br.edu.materdei.tas.core.entity.ProdutoEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */

@Entity
@Table(name = "itempedido")
public class ItemPedidoEntity {
    
    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(nullable = false)
    private Double qtdate;
    
    @Column(nullable = false)
    private Double vlrunit;
    
    @ManyToMany
    @JoinColumn(nullable = false)
    private ProdutoEntity produto;    

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
     * @return the qtdate
     */
    public Double getQtdate() {
        return qtdate;
    }

    /**
     * @param qtdate the qtdate to set
     */
    public void setQtdate(Double qtdate) {
        this.qtdate = qtdate;
    }

    /**
     * @return the vlrunit
     */
    public Double getVlrunit() {
        return vlrunit;
    }

    /**
     * @param vlrunit the vlrunit to set
     */
    public void setVlrunit(Double vlrunit) {
        this.vlrunit = vlrunit;
    }

    /**
     * @return the produto
     */
    public ProdutoEntity getProduto() {
        return produto;
    }

    /**
     * @param produto the produto to set
     */
    public void setProduto(ProdutoEntity produto) {
        this.produto = produto;
    }  
    
}
