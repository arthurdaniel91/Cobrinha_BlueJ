import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.JOptionPane;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Color;
import java.util.Random;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgJogo.*;

/**
 * Classe que controla a interface do programa.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo 
 * @version 2026/06/17 (YYYY/MM/DD)
 */

public class Display{
    //Declaração de classes.
    private ListaDuplamenteLigada<Vector2> lista;
    private InformacoesJogo dados;
    
    //Atributos principais.
    public JFrame janelaPrincipal;
    
    private JPanel painelMatriz;
    private JPanel matrizCelulas;
    
    private JPanel painelInformacoes;
    private JLabel informacaoJogadorLabel;
    private JLabel pontuacaoLabel;
    private JLabel movimentosLabel;
    private JLabel tamanhoCobraLabel;
    private JLabel tamanhoMatrizLabel;
    private JLabel celulasVaziasLabel;
    public JButton botaoConfigurar;
    
    public JFrame janelaConfigurar;
    
    private JPanel painelConfigurar;
    private JLabel tamanhoMatrizConfigurarLabel;
    public JTextField tamanhoMatrizConfigurar;
    private JLabel corVazioConfigurarLabel;
    public JTextField corVazioConfigurar;
    private JLabel corParedeConfigurarLabel;
    public JTextField corParedeConfigurar;
    private JLabel corCaudaConfigurarLabel;
    public JTextField corCaudaConfigurar;
    private JLabel corCabecaConfigurarLabel;
    public JTextField corCabecaConfigurar;
    private JLabel corComidaConfigurarLabel;
    public JTextField corComidaConfigurar;
    private JLabel observacaoRGBConfigurarLabel;
    public JButton botaoConfirmar;
    
    /**
     * Construtor de classe.
     * 
     * @param lista (ListaDuplamenteLigada) A lista a ser utilizada como referência para a cobra.
     * @param dados (InformacoesJogo) Dados sobre o jogo atual.
     */
    
    public Display(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    /**
     * Inicializa a interface do programa.
     */
    
    public void setup(){
        //Cria a janela principal com suas respectivas propriedades.
        janelaPrincipal = new JFrame("Jogo da cobrinha");
        janelaPrincipal.setSize(1200, 800);
        janelaPrincipal.setLocationRelativeTo(null);
        janelaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Forma os paineis que compoem a interface principal.
        formarInformacoes();
        formarMatriz();
        
        //Variável auxiliar para a JLabel da celula.
        JLabel labelCelula;
        
        //Varre a matriz.
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                //Cria a label da celula na coordenada atual com suas respectivas propriedades.
                labelCelula = new JLabel();
                labelCelula.setOpaque(true);
                matrizCelulas.add(labelCelula);
                
                //Define a label para a celula na coordenada atual como a label recem-criada.
                dados.celulas[x][y].label = labelCelula;
            }
        }
        
        // //Revalida o painel matriz.
        // painelMatriz.revalidate();
        // painelMatriz.repaint();
        
        //Deixa a janela principal visivel.
        janelaPrincipal.setVisible(true);
    }
    
    /**
     * Cria o painel que compoem a matriz do jogo.
     */
    
    public void formarMatriz(){
        // if (painelMatriz != null){
            // painelMatriz.removeAll();
        // }
        
        //Cria um novo painel para a matriz com suas respectivas propriedades.
        painelMatriz = new JPanel();
        painelMatriz.setLayout(new GridBagLayout());
        janelaPrincipal.add(painelMatriz);
        
        //Limitações e posicionamento dos elementos do painel.
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        
        //Cria o painel que contem a matriz com suas respectivas propriedades.
        matrizCelulas = new JPanel();
        matrizCelulas.setLayout(new GridLayout(dados.tamanhoMatriz, dados.tamanhoMatriz));
        matrizCelulas.setPreferredSize(new Dimension(800, 800));
        matrizCelulas.setMinimumSize(new Dimension(800, 800));
        matrizCelulas.setMaximumSize(new Dimension(800, 800));
        painelMatriz.add(matrizCelulas, constraints);
        
        // painelMatriz.revalidate();
        // painelMatriz.repaint();
    }
    
    /**
     * Cria o painel que mostra as informacoes do jogo.
     */
    
    public void formarInformacoes(){
        //Cria um novo painel para conter as informacoes do jogo junto de suas respectivas propriedades.
        painelInformacoes = new JPanel();
        painelInformacoes.setLayout(new BoxLayout(painelInformacoes, BoxLayout.Y_AXIS));
        painelInformacoes.setPreferredSize(new Dimension(400, 800));
        painelInformacoes.setMinimumSize(new Dimension(400, 800));
        painelInformacoes.setMaximumSize(new Dimension(400, 800));
        painelInformacoes.setBackground(Color.LIGHT_GRAY);
        janelaPrincipal.add(painelInformacoes, BorderLayout.EAST);
        
        //Criação e posicionamento dos labels que compõem esse painel.
        informacaoJogadorLabel = new JLabel();
        informacaoJogadorLabel.setFont(informacaoJogadorLabel.getFont().deriveFont(36.0f));
        informacaoJogadorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelInformacoes.add(informacaoJogadorLabel);
        
        pontuacaoLabel = new JLabel();
        pontuacaoLabel.setFont(pontuacaoLabel.getFont().deriveFont(24.0f));
        pontuacaoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelInformacoes.add(pontuacaoLabel);
        
        movimentosLabel = new JLabel();
        movimentosLabel.setFont(movimentosLabel.getFont().deriveFont(24.0f));
        movimentosLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelInformacoes.add(movimentosLabel);
        
        tamanhoMatrizLabel = new JLabel();
        tamanhoMatrizLabel.setFont(tamanhoMatrizLabel.getFont().deriveFont(24.0f));
        tamanhoMatrizLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelInformacoes.add(tamanhoMatrizLabel);
        
        tamanhoCobraLabel = new JLabel();
        tamanhoCobraLabel.setFont(tamanhoCobraLabel.getFont().deriveFont(24.0f));
        tamanhoCobraLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelInformacoes.add(tamanhoCobraLabel);
        
        celulasVaziasLabel = new JLabel();
        celulasVaziasLabel.setFont(celulasVaziasLabel.getFont().deriveFont(24.0f));
        celulasVaziasLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        painelInformacoes.add(celulasVaziasLabel);
        
        botaoConfigurar = new JButton("Configurar");
        botaoConfigurar.setFont(botaoConfigurar.getFont().deriveFont(24.0f));
        botaoConfigurar.setAlignmentX(Component.CENTER_ALIGNMENT);
        botaoConfigurar.setFocusable(false);
        painelInformacoes.add(botaoConfigurar);
        
        //Atualiza os labels com as informações respectivas.
        atualizarInformacoes();
    }
    
    /**
     * Varre a matriz e pinta as celulas de suas respectivas cores.
     */
    
    public void desenharMatriz(){
        //Variável auxiliar para o nó sendo acessado atualmente.
        No<Vector2> noAtual = lista.inicio;
        
        //Varre a lista
        for (int i = 0; i < lista.tamanho; i++){
            //Variável auxiliar da posição a ser buscada em porcentagem.
            double t;
            
            //Se a lista for unitária, definir porcentagem como 100%.
            if (lista.tamanho == 1){
                t = 1.0d;
                
            } else {
                //Se não, calcular porcentagem baseada na posição atual e quantidade de elementos na lista.
                t = (double)i / (lista.tamanho - 1);
            }
            
            //Variáveis auxiliares dos valores de cor RGB interpolados para cada parte do corpo da cobra.
            int R = interpolacaoLinear(dados.corCauda.x, dados.corCabeca.x, t);
            int G = interpolacaoLinear(dados.corCauda.y, dados.corCabeca.y, t);
            int B = interpolacaoLinear(dados.corCauda.z, dados.corCabeca.z, t);
            
            //Define a cor da celula a partir dos valores calculados e avança para o próximo nó.
            dados.celulas[noAtual.valor.x][noAtual.valor.y].cor = new Vector3(R, G, B);
            noAtual = noAtual.proximo;
        }
        
        //Varre a matriz
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                //Atributos da celula atual.
                EstadoCelula estadoCelula = dados.celulas[x][y].estado;
                JLabel bordaCelula = dados.celulas[x][y].label;
                
                //Define a cor da celula atual a partir do seu estado.
                switch(estadoCelula){
                    case VAZIO:
                        dados.celulas[x][y].cor = dados.corVazio;
                        
                        break;
                    case PAREDE:
                        dados.celulas[x][y].cor = dados.corParede;
                        
                        break;
                    case COBRA:
                        break;
                    case COMIDA:
                        dados.celulas[x][y].cor = dados.corComida;
                        
                        break;
                }
                
                //Aplica a cor definida para o label da celula.
                dados.celulas[x][y].aplicarCor();
            }
        }
    }
    
    /**
     * Monta a janela configurar ao apertar o botão configurar.
     */
    
    public void montarJanelaConfigurar(){
        //Cria a janela com suas respectivas propriedades.
        janelaConfigurar = new JFrame("Configurações");
        janelaConfigurar.setSize(new Dimension(700, 500));
        
        SpringLayout layout = new SpringLayout();
        painelConfigurar = new JPanel();
        painelConfigurar.setLayout(layout);
        janelaConfigurar.add(painelConfigurar);
        
        //Criação e posicionamento dos labels que compõem o menu configurar.
        tamanhoMatrizConfigurarLabel = new JLabel("Tamanho matriz (8-50): ");
        tamanhoMatrizConfigurarLabel.setFont(tamanhoMatrizConfigurarLabel.getFont().deriveFont(24.0f));
        tamanhoMatrizConfigurar = new JTextField("" + dados.tamanhoMatriz, 15);
        tamanhoMatrizConfigurar.setFont(tamanhoMatrizConfigurar.getFont().deriveFont(24.0f));
        layout.putConstraint(SpringLayout.WEST, tamanhoMatrizConfigurarLabel, 10, SpringLayout.WEST, painelConfigurar);
        layout.putConstraint(SpringLayout.NORTH, tamanhoMatrizConfigurarLabel, 10, SpringLayout.NORTH, painelConfigurar);
        layout.putConstraint(SpringLayout.WEST, tamanhoMatrizConfigurar, 10, SpringLayout.EAST, tamanhoMatrizConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, tamanhoMatrizConfigurar, 0, SpringLayout.NORTH, tamanhoMatrizConfigurarLabel);
        painelConfigurar.add(tamanhoMatrizConfigurarLabel);
        painelConfigurar.add(tamanhoMatrizConfigurar);
        
        corVazioConfigurarLabel = new JLabel("Cor das celulas vazias:");
        corVazioConfigurarLabel.setFont(corVazioConfigurarLabel.getFont().deriveFont(24.0f));
        corVazioConfigurar = new JTextField("" + dados.corVazio.x + ", " + dados.corVazio.y + ", " + dados.corVazio.z, 15);
        corVazioConfigurar.setFont(corVazioConfigurar.getFont().deriveFont(24.0f));
        layout.putConstraint(SpringLayout.WEST, corVazioConfigurarLabel, 0, SpringLayout.WEST, tamanhoMatrizConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corVazioConfigurarLabel, 50, SpringLayout.NORTH, tamanhoMatrizConfigurarLabel);
        layout.putConstraint(SpringLayout.WEST, corVazioConfigurar, 10, SpringLayout.EAST, corVazioConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corVazioConfigurar, 0, SpringLayout.NORTH, corVazioConfigurarLabel);
        painelConfigurar.add(corVazioConfigurarLabel);
        painelConfigurar.add(corVazioConfigurar);
        
        corParedeConfigurarLabel = new JLabel("Cor das paredes:");
        corParedeConfigurarLabel.setFont(corParedeConfigurarLabel.getFont().deriveFont(24.0f));
        corParedeConfigurar = new JTextField("" + dados.corParede.x + ", " + dados.corParede.y + ", " + dados.corParede.z, 15);
        corParedeConfigurar.setFont(corParedeConfigurar.getFont().deriveFont(24.0f));
        layout.putConstraint(SpringLayout.WEST, corParedeConfigurarLabel, 0, SpringLayout.WEST, corVazioConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corParedeConfigurarLabel, 50, SpringLayout.NORTH, corVazioConfigurarLabel);
        layout.putConstraint(SpringLayout.WEST, corParedeConfigurar, 10, SpringLayout.EAST, corParedeConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corParedeConfigurar, 0, SpringLayout.NORTH, corParedeConfigurarLabel);
        painelConfigurar.add(corParedeConfigurarLabel);
        painelConfigurar.add(corParedeConfigurar);
        
        corCaudaConfigurarLabel = new JLabel("Cor da cobra (cauda):");
        corCaudaConfigurarLabel.setFont(corCaudaConfigurarLabel.getFont().deriveFont(24.0f));
        corCaudaConfigurar = new JTextField("" + dados.corCauda.x + ", " + dados.corCauda.y + ", " + dados.corCauda.z, 15);
        corCaudaConfigurar.setFont(corCaudaConfigurar.getFont().deriveFont(24.0f));
        layout.putConstraint(SpringLayout.WEST, corCaudaConfigurarLabel, 0, SpringLayout.WEST, corParedeConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corCaudaConfigurarLabel, 50, SpringLayout.NORTH, corParedeConfigurarLabel);
        layout.putConstraint(SpringLayout.WEST, corCaudaConfigurar, 10, SpringLayout.EAST, corCaudaConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corCaudaConfigurar, 0, SpringLayout.NORTH, corCaudaConfigurarLabel);
        painelConfigurar.add(corCaudaConfigurarLabel);
        painelConfigurar.add(corCaudaConfigurar);
        
        corCabecaConfigurarLabel = new JLabel("Cor da cobra (cabeça):");
        corCabecaConfigurarLabel.setFont(corCabecaConfigurarLabel.getFont().deriveFont(24.0f));
        corCabecaConfigurar = new JTextField("" + dados.corCabeca.x + ", " + dados.corCabeca.y + ", " + dados.corCabeca.z, 15);
        corCabecaConfigurar.setFont(corCabecaConfigurar.getFont().deriveFont(24.0f));
        layout.putConstraint(SpringLayout.WEST, corCabecaConfigurarLabel, 0, SpringLayout.WEST, corCaudaConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corCabecaConfigurarLabel, 50, SpringLayout.NORTH, corCaudaConfigurarLabel);
        layout.putConstraint(SpringLayout.WEST, corCabecaConfigurar, 10, SpringLayout.EAST, corCabecaConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corCabecaConfigurar, 0, SpringLayout.NORTH, corCabecaConfigurarLabel);
        painelConfigurar.add(corCabecaConfigurarLabel);
        painelConfigurar.add(corCabecaConfigurar);
        
        corComidaConfigurarLabel = new JLabel("Cor da comida:");
        corComidaConfigurarLabel.setFont(corComidaConfigurarLabel.getFont().deriveFont(24.0f));
        corComidaConfigurar = new JTextField("" + dados.corComida.x + ", " + dados.corComida.y + ", " + dados.corComida.z, 15);
        corComidaConfigurar.setFont(corComidaConfigurar.getFont().deriveFont(24.0f));
        layout.putConstraint(SpringLayout.WEST, corComidaConfigurarLabel, 0, SpringLayout.WEST, corCabecaConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corComidaConfigurarLabel, 50, SpringLayout.NORTH, corCabecaConfigurarLabel);
        layout.putConstraint(SpringLayout.WEST, corComidaConfigurar, 10, SpringLayout.EAST, corComidaConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, corComidaConfigurar, 0, SpringLayout.NORTH, corComidaConfigurarLabel);
        painelConfigurar.add(corComidaConfigurarLabel);
        painelConfigurar.add(corComidaConfigurar);
        
        observacaoRGBConfigurarLabel = new JLabel("Obs.: Cores em valor RGB (R, G, B), onde 0 <= R, G, B <= 255");
        observacaoRGBConfigurarLabel.setFont(corComidaConfigurarLabel.getFont().deriveFont(12.0f));
        layout.putConstraint(SpringLayout.WEST, observacaoRGBConfigurarLabel, 0, SpringLayout.WEST, corComidaConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, observacaoRGBConfigurarLabel, 50, SpringLayout.NORTH, corComidaConfigurarLabel);
        painelConfigurar.add(observacaoRGBConfigurarLabel);
        
        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setFont(botaoConfirmar.getFont().deriveFont(36.0f));
        layout.putConstraint(SpringLayout.WEST, botaoConfirmar, 0, SpringLayout.WEST, observacaoRGBConfigurarLabel);
        layout.putConstraint(SpringLayout.NORTH, botaoConfirmar, 50, SpringLayout.NORTH, observacaoRGBConfigurarLabel);
        botaoConfirmar.setFocusable(false);
        painelConfigurar.add(botaoConfirmar);
        
        //Deixa a janela visivel.
        janelaConfigurar.setVisible(true);
    }
    
    /**
     * Atualiza as informações mostradas de acordo com o estado atual do jogo.
     */
    
    public void atualizarInformacoes(){
        //Se o jogador estiver vivo.
        if (dados.jogadorVivo == true){
            //Se o jogador venceu o jogo, mudar texto para mostrar vitória.
            if (dados.jogadorVenceu == true){
                informacaoJogadorLabel.setText("<html><center>Jogador venceu, pressione R para começar novo jogo.</center><html>");
                informacaoJogadorLabel.setForeground(Color.CYAN);
                
            } else {
                //Se não, mudar texto para mostrar que o jogador está vivo.
                informacaoJogadorLabel.setText("Jogador vivo");
                informacaoJogadorLabel.setForeground(Color.GREEN);
            }
            
        //Se não, mudar texto para mostrar que o jogador está morto.
        } else {
            informacaoJogadorLabel.setText("<html><center>Jogador morto, pressione R para reiniciar.</center></html>");
            informacaoJogadorLabel.setForeground(Color.RED);
        }
        
        //Atualiza os outros labels com as informações relevantes.
        pontuacaoLabel.setText("Pontuacao: " + dados.pontuacao + " (" + dados.pontuacaoTotal + " total)");
        movimentosLabel.setText("Movimentos: " + dados.movimentos + " (" + dados.movimentosTotais + " totais)");
        tamanhoMatrizLabel.setText("Tamanho matriz: " + dados.tamanhoMatriz + " x " + dados.tamanhoMatriz);
        tamanhoCobraLabel.setText("Tamanho cobra: " + lista.tamanho);
        celulasVaziasLabel.setText("Celulas vazias: " + dados.celulasVazias);
    }
    
    /**
     * Mostra uma mensagem na tela.
     * 
     * @param mensagem (String) A mensagem a ser mostrada.
     */
    
    public void mostrarMensagem(String mensagem){
        //Mostra a mensagem na tela.
        JOptionPane.showMessageDialog(null, mensagem);
    }
    
    /**
     * Obtem o valor entre dois numeros a partir de uma porcentagem (0 a 1)
     * 
     * @param a (Int) Valor minimo.
     * @param b (Int) Valor máximo.
     * @param t (Double) Porcentagem do valor a ser obtido.
     */
    
    private int interpolacaoLinear(int a, int b, double t){
        //Calcula e retorna o número entre A e B a partir de t.
        return (int)(a + (b - a) * t);
    }
}