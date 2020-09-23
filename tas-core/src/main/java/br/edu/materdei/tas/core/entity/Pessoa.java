package br.edu.materdei.tas.core.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 *
 * @author pedro
 */

@MappedSuperclass
public class Pessoa {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column(length = 6, nullable = false)
    private String codigo;
    
    @Column(length = 50, nullable = false)
    private String nome;
    
    @Column(length = 250, nullable = false)
    private String email;
    
    @Column(length = 250)
    private String endereco;
    
    @Column(length = 1, nullable = false)
    private String ativo;

    public Pessoa() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getAtivo() {
        return ativo;
    }
    
    public void setAtivo(String ativo) {
        this.ativo = ativo;
    }  
    
}
