import { environment } from '../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ProdutoEntity } from './produto.service';
import { ClienteEntity } from './cliente.service';

@Injectable({
    providedIn: 'root'
  })
  export class CondicionalService {
  
    constructor(private http: HttpClient) { }
  
    /**
     * Fornece uma lista com TODOS os registros
     */
    public listar() {
      return this.http.get(environment.urlSRV +'/api/condicionais');
    }
  
    /**
     * Verifica se existe um ID no condicional passado por parametro.
     * Se existir, significa que o condicional deverá ser atualizado,
     * caso contrário, deverá ser incluído.
     * 
     * @param condicional 
     */
    public salvar(condicional: CondicionalEntity) {
      if (condicional.id) {
        return this.alterar(condicional);
      } else {
        return this.adicionar(condicional);
      }
    }
  
    public excluir(id: number) {
      return this.http.delete(environment.urlSRV +'/api/condicionais/'+ id);
    }
    
    /**
     * Adiciona um novo registro
     * 
     * @param condicional 
     */
    private adicionar(condicional: CondicionalEntity) {
      return this.http.post(environment.urlSRV +'/api/condicionais', condicional);
    }
  
    /**
     * Altera o registro com o ID passado por parametro na URI
     * @param condicional 
     */
    private alterar(condicional: CondicionalEntity) {
      return this.http.put(environment.urlSRV +'/api/condicionais/'+ condicional.id, condicional);
    }
  }

export class ItemCondicionalEntity {
    id: number;
    qtdade: number;    
    produto: ProdutoEntity;
    situacao: string;

    constructor() {
        this.situacao = 'C';
      }
  }
  
  export class CondicionalEntity {
    id: number;
    codigo: string;
    dtcondicional: Date;
    dtretorno: Date;
    cliente: ClienteEntity;
    itens: ItemCondicionalEntity[];
  
    constructor() {
      this.itens = [];
    }
  }