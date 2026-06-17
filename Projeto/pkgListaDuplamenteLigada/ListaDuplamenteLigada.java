package pkgListaDuplamenteLigada;
import pkgVector.*;

/**
 * Classe contendo os atributos e métodos da lista duplamente ligada usada pelo programa.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/02 (YYYY/MM/DD)
 */

public class ListaDuplamenteLigada<Tipo>{
    //Atributos principais
    public No<Tipo> inicio;
    public No<Tipo> fim;
    public int tamanho;
    
    /**
     * Construtor de classe
     */
    
    public ListaDuplamenteLigada(){
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    
    /**
     * Verifica se a lista está vazia.
     * 
     * @return boolean - Se a lista está vazia ou não.
     */
    
    public boolean checarListaVazia(){
        return (tamanho == 0 || inicio == null);
    }
    
    /**
     * Imprime os elementos da lista.
     */
    
    public void imprimirLista(){
        //Verifica se a lista está vazia.
        if (checarListaVazia()){
            System.out.println("A lista esta vazia.");
            
            return;
        }
        
        //Variável auxiliar.
        No noAtual = this.inicio;
        
        System.out.print("[ ");
        
        //Varre os nós da lista.
        while (true){
            //Se o valor for um Vector2, imprime os sub-valores entre parenteses.
            if (noAtual.valor instanceof Vector2){
                Vector2 valor = (Vector2)noAtual.valor;
                System.out.print("(" + valor.x + ", " + valor.y + ") ");
                
            } else {
                //Se for um valor comum, apenas imprimir.
                System.out.print(noAtual.valor + " ");
            }
            
            //Encerra o loop se for o ultimo nó da lista.
            if (noAtual.proximo == null){
                break;
                
            } else {
                //Se não, continuar varrendo a lista.
                System.out.print("-> ");
                
                noAtual = noAtual.proximo;
            }
        }
        
        System.out.print("]");
    }
    
    /**
     * Esvazia a lista.
     */
    
    public void esvaziarLista(){
        //Declara os nós como nulo e define tamanho 0.
        this.inicio = null;
        this.fim = null;
        this.tamanho = 0;
    }
    
    /**
     * Acrescenta um valor no inicio da lista.
     * 
     * @param valor (Tipo) O tipo de valor a ser acrescentado no inicio lista.
     */
    
    public void adicionarInicio(Tipo valor){
        //Variável auxiliar.
        No<Tipo> novoNo = new No<>(valor);
        
        //Se a lista estiver vazia, declarar inicio e fim como o novo nó criado.
        if (checarListaVazia()){
            this.inicio = novoNo;
            this.fim = novoNo;
            
        } else {
            //Se não, atribuir novo nó antes do nó inicial atual.
            this.inicio.anterior = novoNo;
            novoNo.proximo = this.inicio;
            this.inicio = novoNo;
        }
        
        //Incrementa tamanho da lista.
        tamanho++;
    }
    
    /**
     * Acrescenta um valor no final da lista.
     * 
     * @param valor (Tipo) O tipo de valor a ser acrescentado no final da lista.
     */
    
    public void adicionarFim(Tipo valor){
        //Variável auxiliar.
        No<Tipo> novoNo = new No<>(valor);
        
        //Se a lista estiver vazia, declarar inicio e fim como o novo nó criado.
        if (checarListaVazia()){
            this.inicio = novoNo;
            this.fim = novoNo;
            
        } else {
            //Se não, atribuir novo nó depois do nó final atual.
            this.fim.proximo = novoNo;
            novoNo.anterior = this.fim;
            this.fim = novoNo;
        }
        
        //Incrementa tamanho da lista.
        tamanho++;
    }
    
    /**
     * Remove o primeiro nó da lista.
     */
    
    public void removerInicio(){
        //Se a lista estiver vazia, não fazer nada.
        if (checarListaVazia()) return;
        
        //Se a lista for unitária, declarar lista vazia.
        if (this.tamanho == 1){
            this.esvaziarLista();
            
            return;
        } else {
            //Se não, remover primeiro nó da lista.
            this.inicio = this.inicio.proximo;
            this.inicio.anterior = null;
        }
        
        //Decrementa tamanho da lista.
        tamanho--;
    }
    
    /**
     * Remove o ultimo nó da lista.
     */
    
    public void removerFim(){
        //Se a lista estiver vazia, não fazer nada.
        if (checarListaVazia()) return;
        
        //Se a lista for unitária, declarar lista vazia.
        if (this.tamanho == 1){
            this.esvaziarLista();
            
            return;
        } else {
            //Se não, remover ultimo nó da lista.
            this.fim = this.fim.anterior;
            this.fim.proximo = null;
        }
        
        //Decrementa o tamanho da lista.
        tamanho--;
    }
    
    /**
     * Obtem um nó da lista a partir de um índice numérico.
     * 
     * @param indice (Int) O indice do nó da lista a ser obtido.
     * @return No<Tipo> O nó obtido e o tipo de variável guardado nele.
     */
    
    public No obterPorIndice(int indice){
        //Se a lista estiver vazia ou o indice estiver fora da lista, não fazer nada.
        if (checarListaVazia()) return null;
        if (indice > this.tamanho - 1) return null;
        
        //Variável auxiliar.
        No<Tipo> noAtual = this.inicio;
        
        //Varre a lista até encontrar o nó desejado.
        for (int i = 0; i < indice; i++){
            noAtual = noAtual.proximo;
        }
        
        return noAtual;
    }
}