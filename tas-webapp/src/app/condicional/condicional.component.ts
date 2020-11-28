import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatSidenav } from '@angular/material/sidenav';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ConfirmDialogComponent } from '../_components/confirm-dialog/confirm-dialog.component';
import { ItemCondicionalComponent } from '../_components/item-condicional/item-condicional.component';
import { ClienteEntity, ClienteService } from '../_services/cliente.service';
import { CondicionalEntity, CondicionalService, ItemCondicionalEntity } from '../_services/condicional.service';

@Component({
  selector: 'app-condicional',
  templateUrl: './condicional.component.html',
  styleUrls: ['./condicional.component.scss']
})
export class CondicionalComponent implements OnInit {

  public displayedColumns: string[] = ['codigo', 'dtcondicional', 'dtretorno', 'options'];

  public clientes: ClienteEntity[] = [];
  public condicionais: CondicionalEntity[] = [];
  public condicional: CondicionalEntity = new CondicionalEntity();  

  //Variaveis de controle
  public loading: boolean;
  public editando: boolean;
  public errorMessage: string;

  @ViewChild(MatSidenav, { static: true }) sidenav: MatSidenav;

  constructor(private service: CondicionalService, private clienteService: ClienteService, private snakBar: MatSnackBar,
    private dialog: MatDialog) { }

  /**
   * Método disparado na inicialização do componente, logo após sua construção 
   */
  ngOnInit(): void {
    //Inicia as variáveis
    this.errorMessage = '';
    this.loading = true;

    //Carrega todos os registros
    this.service.listar().subscribe(result => {

      //Carrega os clientes
      this.clienteService.listar().subscribe(result => {
        this.clientes = result as [];
      });

      //Alimenta o datasource da tabela com os registros recebidos da service
      this.condicionais = result as [];

    }, error => {

      //Se ocorreu algum erro neste processo, mostra mensagem para usuário
      this.showError('Ops! Alconteceu algo...', error);

    }).add(() => {

      //Após a execução do subscribe, dando erro ou não, oculta a barra de progresso
      this.loading = false;

    })
  }

  private openSidenav(condicional: CondicionalEntity): void {
    this.condicional = condicional;
    this.sidenav.open();
  }

  private showError(text: string, error: any): void {
    //Mostra a snackbar com fundo customizado (vermelho)
    this.snakBar.open(text, '', {
      duration: 5000,
      panelClass: 'snackWarn'
    });

    //Adiciona a mensagem de erro no painel de erro
    this.errorMessage = (error.status == 0) ? 'Não foi possível conectar ao servidor' : error.message;
  }

  public adicionar(): void {
    this.editando = true;
    this.openSidenav(new CondicionalEntity());
  }

  public visualizar(condicional: CondicionalEntity): void {
    this.editando = false;
    this.openSidenav(condicional);
  }

  public excluir(condicional: CondicionalEntity): void {    
    const dialogRef = this.dialog.open(ConfirmDialogComponent, {
      width: '400px'
    });    
    
    dialogRef.afterClosed().subscribe(result => {
            
      if (result) {
        this.loading = true;

        if (!condicional.dtretorno) {              

          this.service.excluir(condicional.id).subscribe(result => {
          
            this.snakBar.open('Registro excluído com sucesso!', '', {
              duration: 3500
            });
            
            this.ngOnInit();
  
          }, error => {
            
            this.showError('Não foi possível exluir o registro', error);
  
          }).add(() => {
                      
            this.loading = false;
  
          });
          
        } else {

          this.showError('Não é possível excluir condiconal com data de retorno!', null);
          
          this.loading = false;

        }

        
      }
    })
  }

  public confirmar(): void {    
    this.loading = true;
    
    this.service.salvar(this.condicional).subscribe(result => {
      
      this.snakBar.open('Registro salvo com sucesso!', '', {
        duration: 3500
      });
      
      this.ngOnInit();

    }, error => {
      
      this.showError('Não foi possível salvar o registro', error);

    }).add(() => {
           
      this.loading = false;
      
      this.sidenav.close();
    });
  }

  public compareOptions(item1, item2) {
    return item1 && item2 && item1.id === item2.id;
  }

  public addItem(): void {
    let dialogRef = this.dialog.open(ItemCondicionalComponent, {
      width: '500px'
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {        
        this.condicional.itens.push(result);
      }
    })
  }

  public editar(condicoinal: CondicionalEntity): void {
    this.editando = true;
    this.openSidenav(Object.assign({}, condicoinal));
  }

  public alterarSituacaoItem(item: ItemCondicionalEntity, situacao: string): void {
    item.situacao = situacao;
  }

}
