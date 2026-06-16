import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgJogo.*;

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
    
    private JFrame janelaPrincipal;
    private JButton botaoConfigurar;
    private JButton botaoConfirmar;
    
    private boolean menuConfigurarAberto = false;
    
    public Controle(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    public void setup(){
        dados.jogoRodando = false;
        dados.celulas = new Celula[dados.tamanhoMatriz][dados.tamanhoMatriz];
        
        if (lista.checarListaVazia()){
            for (int i = 0; i < dados.tamanhoCobra; i++){
                Vector2 posicao = new Vector2((int)dados.tamanhoMatriz / 2, i + 3);
                
                lista.adicionarFim(posicao);
            }
        }
        
        logica = new Logica(lista, dados);
        logica.setup();
        
        display = new Display(lista, dados);
        display.setup();
        display.desenharMatriz();
        
        botaoConfigurar = display.botaoConfigurar;
        botaoConfigurar.addActionListener(e -> abrirMenuConfiguracoes());
        
        janelaPrincipal = display.janelaPrincipal;
        janelaPrincipal.addKeyListener(this);
    }
    
    public void abrirMenuConfiguracoes(){
        display.montarJanelaConfigurar();
        menuConfigurarAberto = true;
        
        botaoConfirmar = display.botaoConfirmar;
        botaoConfirmar.addActionListener(e -> validarConfiguracoes());
    }
    
    public void validarConfiguracoes(){
        display.fecharMenuConfiguracoes();
        
        for (ActionListener AL : botaoConfirmar.getActionListeners()){
            botaoConfirmar.removeActionListener(AL);
        }
    }
    
    @Override
    public void keyPressed(KeyEvent tecla){
        if (tecla.getKeyCode() == KeyEvent.VK_ENTER && !dados.jogoRodando){
            if (menuConfigurarAberto == true) return;
            dados.jogoRodando = true;
            
            display.atualizarInformacoes();
        }
        
        if (!dados.jogoRodando) return;
        
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