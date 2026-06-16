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
public class Display implements KeyListener{
    private ListaDuplamenteLigada<Vector2> lista;
    private Celula[][] celulas;
    private int tamanho;
    private int celulasVazias;
    
    private JFrame janelaPrincipal;
    
    private JPanel painelMatriz;
    private JPanel matrizCelulas;
    
    private JPanel painelInformacoes;
    
    private JFrame janelaInformacoes;
    private JPanel informacoesJogo;
    private JLabel tamanhoMatrizLabel;
    private JLabel tamanhoCobraLabel;
    private JLabel celulasVaziasLabel;
    
    public Display(ListaDuplamenteLigada lista, int tamanho){
        this.lista = lista;
        this.tamanho = tamanho;
        this.celulas = new Celula[tamanho][tamanho];
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
        matrizCelulas.setLayout(new GridLayout(tamanho, tamanho));
        matrizCelulas.setPreferredSize(new Dimension(800, 800));
        matrizCelulas.setMinimumSize(new Dimension(800, 800));
        matrizCelulas.setMaximumSize(new Dimension(800, 800));
        painelMatriz.add(matrizCelulas, constraints);
        
        painelInformacoes = new JPanel();
        painelInformacoes.setLayout(new BoxLayout(painelInformacoes, BoxLayout.Y_AXIS));
        painelInformacoes.setPreferredSize(new Dimension(400, 800));
        painelInformacoes.setMinimumSize(new Dimension(400, 800));
        painelInformacoes.setMaximumSize(new Dimension(400, 800));
        // painelInformacoes.setBackground(Color.LIGHT_GRAY);
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
        
        // janelaInformacoes = new JFrame("Informações");
        // janelaInformacoes.setSize(400, 400);
        // janelaInformacoes.setLocationRelativeTo(null);
        
        // informacoesJogo = new JPanel();
        // informacoesJogo.setLayout(new BoxLayout(informacoesJogo, BoxLayout.Y_AXIS));
        // janelaInformacoes.add(informacoesJogo);
        
        // tamanhoMatrizLabel = new JLabel();
        // informacoesJogo.add(tamanhoMatrizLabel);
        
        // tamanhoCobraLabel = new JLabel();
        // informacoesJogo.add(tamanhoCobraLabel);
        
        // celulasVaziasLabel = new JLabel();
        // informacoesJogo.add(celulasVaziasLabel);
        
        JLabel labelCelula;
        
        for (int x = 0; x < tamanho; x++){
            for (int y = 0; y < tamanho; y++){
                EstadoCelula estadoCelula;
                
                labelCelula = new JLabel();
                labelCelula.setOpaque(true);
                // labelCelula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                matrizCelulas.add(labelCelula);
                
                if (x == 0 || x == tamanho - 1 || y == 0 || y == tamanho - 1){
                    estadoCelula = EstadoCelula.PAREDE;
                    
                } else {
                    estadoCelula = EstadoCelula.VAZIO;
                }
                
                celulas[x][y] = new Celula(
                    x, //Posição X
                    y, //Posição Y
                    estadoCelula, //Estado atual da celula
                    labelCelula //Ponteiro do label na celula na matriz
                );
            }
        }
        
        No<Vector2> noAtual = lista.inicio;
        
        for (int i = 0; i < lista.tamanho; i++){
            celulas[noAtual.valor.x][noAtual.valor.y].definirEstado(EstadoCelula.COBRA);
            
            noAtual = noAtual.proximo;
        }
        
        janelaPrincipal.addKeyListener(this);
        janelaPrincipal.setVisible(true);
        // janelaInformacoes.setVisible(true);
        
        sortearComida();
        desenharMatriz();
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
            
            
            //(0, 100, 255) - (100, 255, 0)
            int R = interpolacaoLinear(0, 100, t);
            int G = interpolacaoLinear(100, 255, t);
            int B = interpolacaoLinear(255, 0, t);
            // int R = (int)(255 + t * (0 - 255));
            // int G = (int)(255 + t * (100 - 255));
            
            System.out.println("(" + R + ", " + G + ", " + B + ")");
            
            celulas[noAtual.valor.x][noAtual.valor.y].definirCor(new Vector3(R, G, B));
            noAtual = noAtual.proximo;
        }
        
        for (int x = 0; x < tamanho; x++){
            for (int y = 0; y < tamanho; y++){
                EstadoCelula estadoCelula = celulas[x][y].estado;
                JLabel bordaCelula = celulas[x][y].label;
                
                // bordaCelula.setBorder(null);
                
                switch(estadoCelula){
                    case VAZIO:
                        celulas[x][y].definirCor(new Vector3(150, 150, 150));
                        
                        break;
                    case PAREDE:
                        celulas[x][y].definirCor(new Vector3(0, 0, 0));
                        
                        break;
                    case COBRA:
                        break;
                    case COMIDA:
                        // bordaCelula.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
                        celulas[x][y].definirCor(new Vector3(255, 0, 0));
                        
                        break;
                }
                
                celulas[x][y].definirCorLabel();
            }
        }
        
        atualizarInformacoes();
    }
    
    public void atualizarInformacoes(){
        tamanhoMatrizLabel.setText("Tamanho matriz: " + tamanho + " x " + tamanho);
        tamanhoCobraLabel.setText("Tamanho cobra: " + lista.tamanho);
        celulasVaziasLabel.setText("Celulas vazias: " + celulasVazias);
    }
    
    public void sortearComida(){
        Random random = new Random();
        ListaDuplamenteLigada<Vector2> posicoesVazias = new ListaDuplamenteLigada<Vector2>();
        
        for (int x = 0; x < tamanho; x++){
            for (int y = 0; y < tamanho; y++){
                if (celulas[x][y].estado == EstadoCelula.VAZIO) posicoesVazias.adicionarFim(new Vector2(x, y));
            }
        }
        
        celulasVazias = posicoesVazias.tamanho;
        
        if (celulasVazias <= 0){
            System.out.println("Jogador venceu, encerrando jogo.");
            
            System.exit(0);
        }
        
        int numeroAleatorio = random.nextInt(posicoesVazias.tamanho);
        No<Vector2> noSorteado = posicoesVazias.obterPorIndice(numeroAleatorio);
        
        celulas[noSorteado.valor.x][noSorteado.valor.y].definirEstado(EstadoCelula.COMIDA);
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
            // System.out.println("Bateu na parede/no corpo");
            
            return;
        } else if (celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COMIDA){
            obteveComida = true;
        }
        
        lista.adicionarFim(novaPosicao);
        celulas[novaPosicao.x][novaPosicao.y].definirEstado(EstadoCelula.COBRA);
        
        if (obteveComida){
            sortearComida();
            
        } else {
            celulas[cauda.valor.x][cauda.valor.y].definirEstado(EstadoCelula.VAZIO);
            lista.removerInicio();
        }
        
        desenharMatriz();
    }
    
    private int interpolacaoLinear(int a, int b, double t){
        return (int)(a + (b - a) * t);
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
        
        if (tecla.getKeyCode() == KeyEvent.VK_BACK_SPACE){
            System.exit(0);
        }
    }
    
    @Override
    public void keyReleased(KeyEvent tecla){
        
    }
    
    @Override
    public void keyTyped(KeyEvent tecla){
        
    }
}