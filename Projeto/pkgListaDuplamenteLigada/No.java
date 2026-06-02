package pkgListaDuplamenteLigada;
import pkgVector.Vector2;

/**
 * Escreva uma descrição da classe Celula aqui.
 * 
 * @author Arthur Daniel 
 * @version 2026/06/02 (YYYY/MM/DD)
 */

public class No<Tipo>{
    public Tipo valor;
    public No<Tipo> proximo;
    public No<Tipo> anterior;
    
    public No(Tipo valor){
        this.valor = valor;
        this.proximo = null;
        this.anterior = null;
    }
}