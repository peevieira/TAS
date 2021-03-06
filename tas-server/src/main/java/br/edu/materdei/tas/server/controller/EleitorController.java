package br.edu.materdei.tas.server.controller;


import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.server.utils.CustomErrorResponse;
import br.edu.materdei.tas.votacao.entity.EleitorEntity;
import br.edu.materdei.tas.votacao.service.EleitorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EleitorController {
    
    @Autowired
    private EleitorService service;
    
    @GetMapping("eleitores")
    public ResponseEntity<List<EleitorEntity>> findAll() {
        try {
            //Busca TODOS os registros no banco de dados
            List<EleitorEntity> eleitores = service.findAll();

            //Retorna a lista de eleitores
            return new ResponseEntity(eleitores, HttpStatus.OK);  
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PostMapping("eleitores")
    public ResponseEntity create(@RequestBody EleitorEntity eleitor) {
        try {
            //Insere o eleitor no bando de dados
            this.service.save(eleitor);
            
            //Retorna o eleitor inserido
            return new ResponseEntity(eleitor, HttpStatus.CREATED);
            
        } catch (Exception e) {
            
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @GetMapping("eleitores/{id}")
    public ResponseEntity findByID(@PathVariable("id") Integer id) {
        try {
            
            //Verifica se existe um eleitor com o ID passado por parametro
            EleitorEntity eleitor = this.service.findById(id);
            
            //Retorna o eleitor com o ID do parametro
            return new ResponseEntity(eleitor, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de eleitor não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um eleitor com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @PutMapping("eleitores/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id, 
            @RequestBody EleitorEntity eleitor) {
        try {
            //Verifica se existe um eleitor com o ID passado por parametro
            EleitorEntity found = this.service.findById(id);
            
            //Força que o novo objeto tenha o memso ID do objeto localizado
            eleitor.setId(found.getId());
            
            //Salvara o novo objeto no banco
            this.service.save(eleitor);            
            
            //Retorna o objeto que foi atualizado
            return new ResponseEntity(eleitor, HttpStatus.OK);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de eleitor não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um eleitor com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                        
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
    @DeleteMapping("eleitores/{id}")
    public ResponseEntity delete(@PathVariable("id") Integer id) {
        try {
            //Verifica se existe um eleitor com o ID passado por parametro
            EleitorEntity found = this.service.findById(id);
            
            
            //Exclui o item localizado
            this.service.delete(found.getId());
            
            //Como não há o que retornar, retorna-se apenas um status de "Sem Conteúdo"
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            
        } catch (ResourceNotFoundException e) {
            
            //Erro de eleitor não encontrado
            return new ResponseEntity(
                    new CustomErrorResponse("Não existe um eleitor com este código"),
                    HttpStatus.NOT_FOUND);
            
        } catch (Exception e) {
                    
            //Qualquer outro erro
            return new ResponseEntity(
                    new CustomErrorResponse(e.getMessage()), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
