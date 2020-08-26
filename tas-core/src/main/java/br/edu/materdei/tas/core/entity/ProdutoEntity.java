package br.edu.materdei.tas.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author pedro
 */
@Entity
@Table(name = "produto")
public class ProdutoEntity {

    @Id
    @GeneratedValue
    private Integer id;
    
    @Column(length = 6, nullable= false)
    private String codigo;
    
    @Column(length = 50, nullable= false)
    private String nome;
   
    @Lob
    @Column(length = 5000)
    private String descricao;
    
    @Column(nullable= false)
    private Double preco;
    
    @Column(length = 1, nullable= false)
    private String ativo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = false)
    private GrupoEntity grupo;

    public ProdutoEntity() {
        this.ativo = "S";
    }   
    
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public String getAtivo() {
        return ativo;
    }

    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }

    public GrupoEntity getGrupo() {
        return grupo;
    }

    public void setGrupo(GrupoEntity grupo) {
        this.grupo = grupo;
    }    
 
}
