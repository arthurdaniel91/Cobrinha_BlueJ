package pkgListaDuplamenteLigada;
import pkgVector.*;

/**
 * Escreva uma descrição da classe ListaDuplamenteLigada aqui.
 * 
 * @author Arthur Daniel
 * @version 2026/06/02 (YYYY/MM/DD)
 */

public class ListaDuplamenteLigada<Tipo>{
    public No<Tipo> inicio;
    public No<Tipo> fim;
    public int tamanho;
    
    public ListaDuplamenteLigada(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    
    public boolean checarListaVazia(){
        return (tamanho == 0 || inicio == null);
    }
    
    public void imprimirLista(){
        if (checarListaVazia()){
            System.out.println("A lista esta vazia.");
            
            return;
        }
        
        No noAtual = this.inicio;
        
        System.out.print("[ ");
        
        while (true){
            if (noAtual.valor instanceof Vector2){
                Vector2 valor = (Vector2)noAtual.valor;
                System.out.print("(" + valor.x + ", " + valor.y + ") ");
                
            } else {
                System.out.print(noAtual.valor + " ");
            }
            
            if (noAtual.proximo == null){
                break;
                
            } else {
                System.out.print("-> ");
                
                noAtual = noAtual.proximo;
            }
        }
        
        System.out.print("]");
    }
    
    public void adicionarInicio(Tipo valor){
        No<Tipo> novoNo = new No<>(valor);
        
        if (checarListaVazia()){
            this.inicio = novoNo;
            this.fim = novoNo;
            
        } else {
            this.inicio.anterior = novoNo;
            novoNo.proximo = this.inicio;
            this.inicio = novoNo;
        }
        
        tamanho++;
    }
    
    public void adicionarFim(Tipo valor){
        No<Tipo> novoNo = new No<>(valor);
        
        if (checarListaVazia()){
            this.inicio = novoNo;
            this.fim = novoNo;
            
        } else {
            this.fim.proximo = novoNo;
            novoNo.anterior = this.fim;
            this.fim = novoNo;
        }
        
        tamanho++;
    }
    
    public void removerInicio(){
        if (checarListaVazia()) return;
        
        this.inicio = this.inicio.proximo;
        this.inicio.anterior = null;
        
        tamanho--;
    }
    
    public No obterPorIndice(int indice){
        if (checarListaVazia()) return null;
        if (indice > this.tamanho - 1) return null;
        
        No<Tipo> noAtual = this.inicio;
        
        for (int i = 0; i < indice; i++){
            noAtual = noAtual.proximo;
        }
        
        return noAtual;
    }
}