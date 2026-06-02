package pkgListaDuplamenteLigada;

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
    
    public void adicionarFim(Tipo valor){
        No<Tipo> NovoNo = new No<>(valor);
        
        if (checarListaVazia()){
            this.inicio = NovoNo;
            this.fim = NovoNo;
            
        } else {
            this.fim.proximo = NovoNo;
            NovoNo.anterior = this.fim;
            this.fim = NovoNo;
        }
        
        tamanho++;
    }
}