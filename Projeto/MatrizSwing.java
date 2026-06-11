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
    
    private JLabel labelCelula;
    
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
        
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                Vector3 cor;
                
                labelCelula = new JLabel();
                labelCelula.setOpaque(true);
                labelCelula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matrizCelulas.add(labelCelula);
                
                celulas[i][j] = new Celula(
                    i, //Posição X
                    j, //Posição Y
                    labelCelula //Ponteiro do label na matriz
                );
                
                if (i == 0 || i == tamanho - 1 || j == 0 || j == tamanho - 1){
                    cor = new Vector3(0, 0, 0);
                    
                } else {
                    cor = new Vector3(0, 175, 0);
                }
                
                celulas[i][j].definirCor(cor);
            }
        }
        
        janelaPrincipal.addKeyListener(this);
        janelaPrincipal.setVisible(true);
    }
    
    public void atualizarMatriz(){
        No<Vector2> noAtual = lista.inicio;
        Vector3 cor;
        
        for (int i = 0; i < lista.tamanho; i++){
            if (noAtual == lista.inicio){
                cor = new Vector3(0, 255, 255);
                
            } else if (noAtual == lista.fim){
                cor = new Vector3(0, 0, 255);
                
            } else {
                cor = new Vector3(0, 150, 255);
            }
            
            celulas[noAtual.valor.x][noAtual.valor.y].definirCor(cor);
            noAtual = noAtual.proximo;
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