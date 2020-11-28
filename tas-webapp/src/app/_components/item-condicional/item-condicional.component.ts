import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ItemCondicionalEntity } from 'src/app/_services/condicional.service';
import { ProdutoEntity, ProdutoService } from 'src/app/_services/produto.service';

@Component({
  selector: 'app-item-condicional',
  templateUrl: './item-condicional.component.html',
  styleUrls: ['./item-condicional.component.scss']
})
export class ItemCondicionalComponent {

  public itemCondicional: ItemCondicionalEntity;
  public produtos: ProdutoEntity[] = [];

  constructor(private produtoService: ProdutoService, public dialogRef: MatDialogRef<ItemCondicionalComponent>) { 
    this.itemCondicional = new ItemCondicionalEntity();

    //Carrego todos os produtos
    this.produtoService.listar().subscribe(result => {
      this.produtos = result as [];
      this.produtos = this.produtos.filter(produto => produto.aceitacondicional === 'S');
    });
  }

  public onDismiss(): void {
    this.dialogRef.close(false);
  }

  public onConfirm(): void {
    this.dialogRef.close(this.itemCondicional);
  }

  public changeItem(): void {    
    this.itemCondicional.qtdade = 1;
  }

}
