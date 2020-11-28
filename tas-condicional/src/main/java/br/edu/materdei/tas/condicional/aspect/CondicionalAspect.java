/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.materdei.tas.condicional.aspect;

import br.edu.materdei.tas.condicional.entity.CondicionalEntity;
import br.edu.materdei.tas.condicional.service.CondicionalService;
import br.edu.materdei.tas.core.exception.ResourceNotFoundException;
import br.edu.materdei.tas.core.service.ProdutoService;
import br.edu.materdei.tas.venda.entity.ItemPedidoEntity;
import br.edu.materdei.tas.venda.entity.PedidoEntity;
import br.edu.materdei.tas.venda.entity.VendaEntity;
import br.edu.materdei.tas.venda.service.PedidoService;
import br.edu.materdei.tas.venda.service.VendaService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author pedro
 */

@Aspect
@Component
public class CondicionalAspect {
    
    @Autowired
    private ProdutoService produtoService;
    
    @Autowired
    private PedidoService pedidoService;
    
    @Autowired
    private VendaService vendaService;
    
    @Autowired
    private CondicionalService condicionalService;
    
    @AfterReturning(pointcut = "execution( * br.edu.materdei.tas.condicional.service.CondicionalService.save(..))")
    public void salvarCondicional(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        CondicionalEntity condicional = (CondicionalEntity) args[0];
        
        if (condicional.getDtretorno() != null) {
            
            PedidoEntity pedido = new PedidoEntity();
           
            pedido.setCliente(condicional.getCliente());
            pedido.setCodigo("C-"+ condicional.getCodigo());
            pedido.setDtpedido(condicional.getDtcondicional());
            pedido.setDtfaturado(condicional.getDtretorno());
            
            List<ItemPedidoEntity> itensPedido = new ArrayList<ItemPedidoEntity>();
            
            condicional.getItens().forEach(item -> {            
            
                if (item.getSituacao().equals("V")) {
                    
                    ItemPedidoEntity itemPedido = new ItemPedidoEntity();
                    
                    
                    itemPedido.setProduto(item.getProduto());
                    itemPedido.setQtdade(item.getQtdade());
                    
                    try {                    
                        itemPedido.setVlrunit(this.produtoService.findById(item.getProduto().getId()).getPreco());
                    } catch (ResourceNotFoundException ex) {
                        Logger.getLogger(CondicionalAspect.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    itensPedido.add(itemPedido);
                    
                }
                
            });
            
            if (!itensPedido.isEmpty()) {
                
                pedido.setItens(itensPedido);                
                this.pedidoService.save(pedido);
            
                VendaEntity venda = new VendaEntity();

                venda.setCodigo("C-"+ condicional.getCodigo());
                venda.setDtvenda(condicional.getDtretorno());
                venda.setPedido(pedido);

                this.vendaService.save(venda);
                
            }          
            
        }
    }
    
    
    
}
