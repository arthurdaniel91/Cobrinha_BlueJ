import java.awt.*;
import javax.swing.*;
import pkgVector.*;

/**
 * Escreva uma descrição da classe MatrizSwing aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class MatrizSwing{
    private Celula[][] celulas;
    private int tamanho;
    
    private JFrame janelaPrincipal;
    
    private JPanel matrizCelulas;
    
    private JLabel labelCelula;
    
    public MatrizSwing(int tamanho){
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
                labelCelula = new JLabel();
                labelCelula.setOpaque(true);
                labelCelula.setBackground(new Color(0, 175, 0)); //145, 255, 105
                labelCelula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matrizCelulas.add(labelCelula);
                
                celulas[i][j] = new Celula(
                    i, //Posição X
                    j, //Posição Y
                    labelCelula //Ponteiro do label na matriz
                );
            }
        }
        
        Vector3 branco = new Vector3(0, 0, 0);
        
        celulas[10][10].definirCor(branco);
        
        janelaPrincipal.setVisible(true);
    }
}