import javax.swing.JLabel;
import java.util.Random;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgJogo.*;

/**
 * Classe que controla a lógica do jogo.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo 
 * @version 2026/06/17 (YYYY/MM/DD)
 */

public class Logica{
    //Declaração de classes.
    private ListaDuplamenteLigada lista;
    private InformacoesJogo dados;
    
    /**
     * Construtor de classe.
     * 
     * @param lista (ListaDuplamenteLigada) A lista a ser utilizada como referência para a cobra.
     * @param dados (InformacoesJogo) Dados sobre o jogo atual.
     */
    
    public Logica(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    /**
     * Inicializa a lógica do jogo.
     */
    
    public void setup(){
        //Varre a matriz de células
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                //Variável auxiliar para o estado da celula.
                EstadoCelula estadoCelula;
                
                //Se a celula estiver na borda da matriz, declarar como parede.
                if (x == 0 || x == dados.tamanhoMatriz - 1 || y == 0 || y == dados.tamanhoMatriz - 1){
                    estadoCelula = EstadoCelula.PAREDE;
                    
                } else {
                    //Se não, declarar como célula vazia.
                    estadoCelula = EstadoCelula.VAZIO;
                }
                
                //Constroi a classe celula na coordenada atual.
                dados.celulas[x][y] = new Celula(estadoCelula);
            }
        }
        
        //Variável auxiliar para nó atualmente sendo acessado.
        No<Vector2> noAtual = lista.inicio;
        
        //Varre a lista
        for (int i = 0; i < lista.tamanho; i++){
            //Acessa os valores dos nós e declara as respectivas células como corpo da cobra.
            dados.celulas[noAtual.valor.x][noAtual.valor.y].estado = EstadoCelula.COBRA;
            
            //Atualiza o nó sendo acessado.
            noAtual = noAtual.proximo;
        }
        
        //Sorteia uma posição para a comida.
        sortearComida();
    }
    
    /**
     * Movimenta a cobra de acordo com a direção indicada.
     * 
     * @param direcao (Vector2) A direção na qual a cobra irá andar.
     */
    
    public void movimentarCobra(Vector2 direcao){
        //Variáveis auxiliares para partes do corpo da cobra e a posição da nova celula.
        No<Vector2> cabeca = lista.fim;
        No<Vector2> pescoco = lista.fim.anterior;
        No<Vector2> cauda = lista.inicio;
        Vector2 novaPosicao = new Vector2(cabeca.valor.x - direcao.y, cabeca.valor.y + direcao.x);
        
        //Variáveis auxiliares para
        boolean obteveComida = false;
        boolean moveuParaCauda = false;
        
        //Se a cobra estiver prestes a movimentar a uma celula ocupada.
        if (dados.celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.PAREDE || dados.celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COBRA){
            //Se for a cauda, declarar que vai mover para a cauda e continua o movimento.
            if (novaPosicao.x == cauda.valor.x && novaPosicao.y == cauda.valor.y){
                moveuParaCauda = true;
                
            } else {
                //Se for o pescoço (evitar de morrer ao andar na direção oposta da ultima direção andada), apenas encerrar movimento.
                if (novaPosicao.x == pescoco.valor.x && novaPosicao.y == pescoco.valor.y){
                    return;
                }
                
                //Se for o corpo, declarar jogador morto e encerrar movimento.
                dados.jogadorVivo = false;
                return;
            }
        
        //Se for a comida, declarar comida obtida e continuar o movimento.
        } else if (dados.celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COMIDA){
            obteveComida = true;
        }
        
        //Acrescenta a nova posição no final da lista para simular movimento.
        lista.adicionarFim(novaPosicao);
        dados.celulas[novaPosicao.x][novaPosicao.y].estado = EstadoCelula.COBRA;
        
        //Se a comida foi obtida, incrementar pontuação e sortear nova posição para a comida.
        if (obteveComida){
            dados.pontuacao++;
            dados.pontuacaoTotal++;
            
            sortearComida();
        } else {
            //Se a cobrinha não moveu para a cauda, declarar celula como vazia para simular movimento.
            if (!moveuParaCauda){
                dados.celulas[cauda.valor.x][cauda.valor.y].estado = EstadoCelula.VAZIO;
            }
            
            //Remove primeiro elemento da lista para atualizar posição do corpo da cobra.
            lista.removerInicio();
        }
        
        //Incrementa número de movimentos.
        dados.movimentos++;
        dados.movimentosTotais++;
    }
    
    /**
     * Sorteia uma nova posição para comida, encerrando o jogo se não houver uma posição disponivel.
     */
    
    public void sortearComida(){
        //Variáveis auxiliares para elemento aleatório e lista de posições vazias.
        Random random = new Random();
        ListaDuplamenteLigada<Vector2> posicoesVazias = new ListaDuplamenteLigada<Vector2>();
        
        //Varre a matriz
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                //Se a celula estiver vazia, acrescentar a lista de posições vazias.
                if (dados.celulas[x][y].estado == EstadoCelula.VAZIO) posicoesVazias.adicionarFim(new Vector2(x, y));
            }
        }
        
        //Atualiza o número de celulas vazias do jogo.
        dados.celulasVazias = posicoesVazias.tamanho;
        
        //Se não houver celulas vazias, declarar jogo vencido e parar aqui.
        if (dados.celulasVazias <= 0){
            dados.jogadorVenceu = true;
            
            return;
        }
        
        //Seleciona um número aleatório para selecionar um nó.
        int numeroAleatorio = random.nextInt(posicoesVazias.tamanho);
        No<Vector2> noSorteado = posicoesVazias.obterPorIndice(numeroAleatorio);
        
        //Atualiza a celula selecionada.
        dados.celulas[noSorteado.valor.x][noSorteado.valor.y].estado = EstadoCelula.COMIDA;
    }
}