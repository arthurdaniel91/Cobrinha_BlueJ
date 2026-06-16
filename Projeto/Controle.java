import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;

/**
 * Escreva uma descrição da classe Controle aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Controle implements KeyListener{
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
        logica.setup();
        
        display = new Display(lista, dados);
        display.setup();
        display.desenharMatriz();
        
        JFrame janelaPrincipal = display.janelaPrincipal;
        janelaPrincipal.addKeyListener(this);
    }
    
    @Override
    public void keyPressed(KeyEvent tecla){
        if (tecla.getKeyCode() == KeyEvent.VK_UP){
            logica.movimentarCobra(new Vector2(0, 1));
            display.desenharMatriz();
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN){
            logica.movimentarCobra(new Vector2(0, -1));
            display.desenharMatriz();
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT){
            logica.movimentarCobra(new Vector2(1, 0));
            display.desenharMatriz();
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT){
            logica.movimentarCobra(new Vector2(-1, 0));
            display.desenharMatriz();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent tecla){
        
    }
    
    @Override
    public void keyTyped(KeyEvent tecla){
        
    }
}