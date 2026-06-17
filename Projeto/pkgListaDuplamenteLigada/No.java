package pkgListaDuplamenteLigada;
import pkgVector.Vector2;

/**
 * Classe contendo os atributos principais em uma lista duplamente ligada.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/02 (YYYY/MM/DD)
 */

public class No<Tipo>{
    //Atributos principais.
    public Tipo valor;
    public No<Tipo> proximo;
    public No<Tipo> anterior;
    
    /**
     * Constutor de classe.
     * 
     * @param valor (Tipo) O valor a ser guardado no nó.
     */
    
    public No(Tipo valor){
        this.valor = valor;
        this.proximo = null;
        this.anterior = null;
    }
}