import pkgListaDuplamenteLigada.*;
import pkgVector.*;

/**
 * Escreva uma descrição da classe Controle aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Controle{
    private ListaDuplamenteLigada lista;
    private Logica logica;
    private Display display;
    
    private int tamanhoMatriz;
    private int tamanhoCobra;
    
    public Controle(ListaDuplamenteLigada lista){
        this.lista = lista;
        this.tamanhoMatriz = tamanhoMatriz;
        this.tamanhoCobra = tamanhoCobra;
    }
    
    public void setup(int tamanhoMatriz, int tamanhoCobra){
        if (lista.checarListaVazia()){
            for (int i = 0; i < tamanhoCobra; i++){
                Vector2 posicao = new Vector2((int)tamanhoMatriz / 2, i + 3);
                
                lista.adicionarFim(posicao);
            }
        }
        
        logica = new Logica(lista);
        
        display = new Display(lista, tamanhoMatriz);
        display.setup();
        
        this.tamanhoMatriz = tamanhoMatriz;
        this.tamanhoCobra = tamanhoCobra;
    }
}