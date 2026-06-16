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
    private Celula[][] celulas;
    
    public Controle(ListaDuplamenteLigada lista){
        this.lista = lista;
    }
    
    public void setup(int tamanhoMatriz, int tamanhoCobra){
        if (lista.checarListaVazia()){
            for (int i = 0; i < tamanhoCobra; i++){
                Vector2 posicao = new Vector2((int)tamanhoMatriz / 2, i + 3);
                
                lista.adicionarFim(posicao);
            }
        }
        
        this.tamanhoMatriz = tamanhoMatriz;
        this.tamanhoCobra = tamanhoCobra;
        this.celulas = new Celula[tamanhoMatriz][tamanhoMatriz];
        
        logica = new Logica(lista, celulas);
        display = new Display(lista, tamanhoMatriz, celulas);
        display.setup();
    }
}