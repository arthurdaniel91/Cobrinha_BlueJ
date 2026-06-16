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
    private InformacoesJogo dados;
    private Logica logica;
    private Display display;
    
    public Controle(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    public void setup(int tamanhoMatriz, int tamanhoCobra){
        if (lista.checarListaVazia()){
            for (int i = 0; i < tamanhoCobra; i++){
                Vector2 posicao = new Vector2((int)tamanhoMatriz / 2, i + 3);
                
                lista.adicionarFim(posicao);
            }
        }
        
        dados.tamanhoMatriz = tamanhoMatriz;
        dados.celulas = new Celula[tamanhoMatriz][tamanhoMatriz];
        
        logica = new Logica(lista, dados);
        display = new Display(lista, tamanhoMatriz, dados);
        display.setup();
    }
}