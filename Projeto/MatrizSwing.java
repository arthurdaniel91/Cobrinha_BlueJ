import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;

/**
 * Escreva uma descrição da classe MatrizSwing aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class MatrizSwing implements KeyListener{
    private ListaDuplamenteLigada<Vector2> lista = new ListaDuplamenteLigada<Vector2>();
    private Celula[][] celulas;
    private int tamanho;
    
    private JFrame janelaPrincipal;
    private JPanel matrizCelulas;
    
    public MatrizSwing(ListaDuplamenteLigada lista, int tamanho){
        this.lista = lista;
        this.tamanho = tamanho;
        this.celulas = new Celula[tamanho][tamanho];
    }
    
    public void setup(){
        janelaPrincipal = new JFrame("Snake");
        janelaPrincipal.setSize(800, 800);
        
        matrizCelulas = new JPanel();
        matrizCelulas.setLayout(new GridLayout(tamanho, tamanho));
        janelaPrincipal.add(matrizCelulas);
        
        JLabel labelCelula;
        
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                EstadoCelula estadoCelula;
                
                labelCelula = new JLabel();
                labelCelula.setOpaque(true);
                // labelCelula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matrizCelulas.add(labelCelula);
                
                if (i == 0 || i == tamanho - 1 || j == 0 || j == tamanho - 1){
                    estadoCelula = EstadoCelula.PAREDE;
                    
                } else {
                    estadoCelula = EstadoCelula.VAZIO;
                }
                
                celulas[i][j] = new Celula(
                    i, //Posição X
                    j, //Posição Y
                    estadoCelula, //Estado atual da celula
                    labelCelula //Ponteiro do label na matriz
                );
            }
        }
        
        janelaPrincipal.addKeyListener(this);
        janelaPrincipal.setVisible(true);
    }
    
    public void atualizarMatriz(){
        No<Vector2> noAtual = lista.inicio;
        
        for (int i = 0; i < lista.tamanho; i++){
            celulas[noAtual.valor.x][noAtual.valor.y].estado = EstadoCelula.COBRA;
            
            noAtual = noAtual.proximo;
        }
        
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                EstadoCelula estadoCelula = celulas[i][j].estado;
                
                if (estadoCelula == EstadoCelula.VAZIO){
                    celulas[i][j].definirCor(new Vector3(0, 255, 100));
                }
                
                if (estadoCelula == EstadoCelula.PAREDE){
                    celulas[i][j].definirCor(new Vector3(0, 0, 0));
                }
                
                if (estadoCelula == EstadoCelula.COBRA){
                    celulas[i][j].definirCor(new Vector3(0, 100, 255));
                }
            }
        }
    }
    
    @Override
    public void keyPressed(KeyEvent tecla){
        if (tecla.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("Seta pra cima");
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("Seta pra baixo");
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("Seta pra esquerda");
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT){
            System.out.println("Seta pra direita");
        }
    }
    
    @Override
    public void keyReleased(KeyEvent tecla){
        
    }
    
    @Override
    public void keyTyped(KeyEvent tecla){
        
    }
}