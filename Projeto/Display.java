import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;

/**
 * Escreva uma descrição da classe Display aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Display{
    private ListaDuplamenteLigada<Vector2> lista;
    private InformacoesJogo dados;
    
    public JFrame janelaPrincipal;
    
    private JPanel painelMatriz;
    private JPanel matrizCelulas;
    
    private JPanel painelInformacoes;
    private JLabel tamanhoMatrizLabel;
    private JLabel tamanhoCobraLabel;
    private JLabel celulasVaziasLabel;
    
    public Display(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    public void setup(){
        janelaPrincipal = new JFrame("Snake");
        janelaPrincipal.setSize(1200, 800);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        painelMatriz = new JPanel();
        painelMatriz.setLayout(new GridBagLayout());
        janelaPrincipal.add(painelMatriz);
        
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        
        matrizCelulas = new JPanel();
        matrizCelulas.setLayout(new GridLayout(dados.tamanhoMatriz, dados.tamanhoMatriz));
        matrizCelulas.setPreferredSize(new Dimension(800, 800));
        matrizCelulas.setMinimumSize(new Dimension(800, 800));
        matrizCelulas.setMaximumSize(new Dimension(800, 800));
        painelMatriz.add(matrizCelulas, constraints);
        
        painelInformacoes = new JPanel();
        painelInformacoes.setLayout(new BoxLayout(painelInformacoes, BoxLayout.Y_AXIS));
        painelInformacoes.setPreferredSize(new Dimension(400, 800));
        painelInformacoes.setMinimumSize(new Dimension(400, 800));
        painelInformacoes.setMaximumSize(new Dimension(400, 800));
        janelaPrincipal.add(painelInformacoes, BorderLayout.EAST);
        
        tamanhoMatrizLabel = new JLabel();
        tamanhoMatrizLabel.setFont(tamanhoMatrizLabel.getFont().deriveFont(24.0f));
        tamanhoMatrizLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelInformacoes.add(tamanhoMatrizLabel);
        
        tamanhoCobraLabel = new JLabel();
        tamanhoCobraLabel.setFont(tamanhoCobraLabel.getFont().deriveFont(24.0f));
        tamanhoCobraLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelInformacoes.add(tamanhoCobraLabel);
        
        celulasVaziasLabel = new JLabel();
        celulasVaziasLabel.setFont(celulasVaziasLabel.getFont().deriveFont(24.0f));
        celulasVaziasLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelInformacoes.add(celulasVaziasLabel);
        
        JLabel labelCelula;
        
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                labelCelula = new JLabel();
                labelCelula.setOpaque(true);
                matrizCelulas.add(labelCelula);
                
                dados.celulas[x][y].label = labelCelula;
            }
        }
        
        janelaPrincipal.setVisible(true);
    }
    
    public void desenharMatriz(){
        No<Vector2> noAtual = lista.inicio;
        
        for (int i = 0; i < lista.tamanho; i++){
            double t;
            
            if (lista.tamanho == 1){
                t = 1.0d;
                
            } else {
                t = (double)i / (lista.tamanho - 1);
            }
            
            int R = interpolacaoLinear(0, 100, t);
            int G = interpolacaoLinear(100, 255, t);
            int B = interpolacaoLinear(255, 0, t);
            
            dados.celulas[noAtual.valor.x][noAtual.valor.y].cor = new Vector3(R, G, B);
            noAtual = noAtual.proximo;
        }
        
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                EstadoCelula estadoCelula = dados.celulas[x][y].estado;
                JLabel bordaCelula = dados.celulas[x][y].label;
                
                switch(estadoCelula){
                    case VAZIO:
                        dados.celulas[x][y].cor = new Vector3(150, 150, 150);
                        
                        break;
                    case PAREDE:
                        dados.celulas[x][y].cor = new Vector3(0, 0, 0);
                        
                        break;
                    case COBRA:
                        break;
                    case COMIDA:
                        dados.celulas[x][y].cor = new Vector3(255, 0, 0);
                        
                        break;
                }
                
                dados.celulas[x][y].aplicarCor();
            }
        }
        
        atualizarInformacoes();
    }
    
    public void atualizarInformacoes(){
        tamanhoMatrizLabel.setText("Tamanho matriz: " + dados.tamanhoMatriz + " x " + dados.tamanhoMatriz);
        tamanhoCobraLabel.setText("Tamanho cobra: " + lista.tamanho);
        celulasVaziasLabel.setText("Celulas vazias: " + dados.celulasVazias);
    }
    
    private int interpolacaoLinear(int a, int b, double t){
        return (int)(a + (b - a) * t);
    }
    
    // @Override
    // public void keyPressed(KeyEvent tecla){
        // if (tecla.getKeyCode() == KeyEvent.VK_UP){
            // movimentarCobra(new Vector2(0, 1));
        // }
        
        // if (tecla.getKeyCode() == KeyEvent.VK_DOWN){
            // movimentarCobra(new Vector2(0, -1));
        // }
        
        // if (tecla.getKeyCode() == KeyEvent.VK_RIGHT){
            // movimentarCobra(new Vector2(1, 0));
        // }
        
        // if (tecla.getKeyCode() == KeyEvent.VK_LEFT){
            // movimentarCobra(new Vector2(-1, 0));
        // }
        
        // if (tecla.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            // System.exit(0);
        // }
    // }
    
    // @Override
    // public void keyReleased(KeyEvent tecla){
        
    // }
    
    // @Override
    // public void keyTyped(KeyEvent tecla){
        
    // }
}