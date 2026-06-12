import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import java.util.Random;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;

/**
 * Escreva uma descrição da classe MatrizSwing aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class MatrizSwing implements KeyListener{
    private ListaDuplamenteLigada<Vector2> lista;
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
        
        No<Vector2> noAtual = lista.inicio;
        
        for (int i = 0; i < lista.tamanho; i++){
            celulas[noAtual.valor.x][noAtual.valor.y].estado = EstadoCelula.COBRA;
            
            noAtual = noAtual.proximo;
        }
        
        janelaPrincipal.addKeyListener(this);
        janelaPrincipal.setVisible(true);
        
        sortearComida();
        desenharMatriz();
    }
    
    public void desenharMatriz(){
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                EstadoCelula estadoCelula = celulas[i][j].estado;
                
                switch(estadoCelula){
                    case VAZIO:
                        celulas[i][j].definirCor(new Vector3(0, 255, 100));
                        
                        break;
                    case PAREDE:
                        celulas[i][j].definirCor(new Vector3(0, 0, 0));
                        
                        break;
                    case COBRA:
                        celulas[i][j].definirCor(new Vector3(0, 100, 255));
                        
                        break;
                    case COMIDA:
                        celulas[i][j].definirCor(new Vector3(255, 0, 0));
                        
                        break;
                }
            }
        }
    }
    
    public void sortearComida(){
        Random random = new Random();
        ListaDuplamenteLigada<Vector2> posicoesVazias = new ListaDuplamenteLigada<Vector2>();
        
        for (int i = 0; i < tamanho; i++){
            for (int j = 0; j < tamanho; j++){
                if (celulas[i][j].estado == EstadoCelula.VAZIO) posicoesVazias.adicionarFim(new Vector2(i, j));
            }
        }
        
        int numeroAleatorio = random.nextInt(posicoesVazias.tamanho);
        No<Vector2> noSorteado = posicoesVazias.obterPorIndice(numeroAleatorio);
        
        celulas[noSorteado.valor.x][noSorteado.valor.y].estado = EstadoCelula.COMIDA;
    }
    
    public void movimentarCobra(Vector2 direcao){
        // if (direcao.y == 1){
            // System.out.println("Seta pra cima");
        // }
        
        // if (direcao.y == -1){
            // System.out.println("Seta pra baixo");
        // }
        
        // if (direcao.x == 1){
            // System.out.println("Seta pra direita");
        // }
        
        // if (direcao.x == -1){
            // System.out.println("Seta pra esquerda");
        // }
        
        No<Vector2> cabeca = lista.fim;
        No<Vector2> cauda = lista.inicio;
        Vector2 novaPosicao = new Vector2(cabeca.valor.x - direcao.y, cabeca.valor.y + direcao.x);
        boolean obteveComida = false;
        
        if (celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.PAREDE || celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COBRA){
            System.out.println("Bateu na parede/no corpo");
            
            return;
        } else if (celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COMIDA){
            obteveComida = true;
        }
        
        lista.adicionarFim(novaPosicao);
        celulas[novaPosicao.x][novaPosicao.y].estado = EstadoCelula.COBRA;
        
        if (obteveComida){
            sortearComida();
            
            
        } else {
            celulas[cauda.valor.x][cauda.valor.y].estado = EstadoCelula.VAZIO;
            lista.removerInicio();
        }
        
        desenharMatriz();
    }
    
    @Override
    public void keyPressed(KeyEvent tecla){
        if (tecla.getKeyCode() == KeyEvent.VK_UP){
            movimentarCobra(new Vector2(0, 1));
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN){
            movimentarCobra(new Vector2(0, -1));
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT){
            movimentarCobra(new Vector2(1, 0));
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT){
            movimentarCobra(new Vector2(-1, 0));
        }
    }
    
    @Override
    public void keyReleased(KeyEvent tecla){
        
    }
    
    @Override
    public void keyTyped(KeyEvent tecla){
        
    }
}