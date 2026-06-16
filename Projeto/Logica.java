import javax.swing.JLabel;
import java.util.Random;
import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgJogo.*;

/**
 * Escreva uma descrição da classe Logica aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */
public class Logica{
    private ListaDuplamenteLigada lista;
    private InformacoesJogo dados;
    
    public Logica(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    public void setup(){
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                EstadoCelula estadoCelula;
                
                if (x == 0 || x == dados.tamanhoMatriz - 1 || y == 0 || y == dados.tamanhoMatriz - 1){
                    estadoCelula = EstadoCelula.PAREDE;
                    
                } else {
                    estadoCelula = EstadoCelula.VAZIO;
                }
                
                dados.celulas[x][y] = new Celula(
                    x, //Posição X
                    y, //Posição Y
                    estadoCelula //Estado da celula
                );
            }
        }
        
        No<Vector2> noAtual = lista.inicio;
        
        for (int i = 0; i < lista.tamanho; i++){
            dados.celulas[noAtual.valor.x][noAtual.valor.y].estado = EstadoCelula.COBRA;
            
            noAtual = noAtual.proximo;
        }
        
        sortearComida();
    }
    
    public void movimentarCobra(Vector2 direcao){
        No<Vector2> cabeca = lista.fim;
        No<Vector2> cauda = lista.inicio;
        Vector2 novaPosicao = new Vector2(cabeca.valor.x - direcao.y, cabeca.valor.y + direcao.x);
        
        boolean obteveComida = false;
        boolean moveuParaCauda = false;
        
        if (dados.celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.PAREDE || dados.celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COBRA){
            if (cauda.valor.x == novaPosicao.x && cauda.valor.y == novaPosicao.y){
                moveuParaCauda = true;
                
            } else {
                return;
            }
            
        } else if (dados.celulas[novaPosicao.x][novaPosicao.y].estado == EstadoCelula.COMIDA){
            obteveComida = true;
        }
        
        lista.adicionarFim(novaPosicao);
        dados.celulas[novaPosicao.x][novaPosicao.y].estado = EstadoCelula.COBRA;
        
        if (obteveComida){
            sortearComida();
            
        } else {
            if (!moveuParaCauda){
                dados.celulas[cauda.valor.x][cauda.valor.y].estado = EstadoCelula.VAZIO;
            }
            
            lista.removerInicio();
        }
    }
    
    public void sortearComida(){
        Random random = new Random();
        ListaDuplamenteLigada<Vector2> posicoesVazias = new ListaDuplamenteLigada<Vector2>();
        
        for (int x = 0; x < dados.tamanhoMatriz; x++){
            for (int y = 0; y < dados.tamanhoMatriz; y++){
                if (dados.celulas[x][y].estado == EstadoCelula.VAZIO) posicoesVazias.adicionarFim(new Vector2(x, y));
            }
        }
        
        dados.celulasVazias = posicoesVazias.tamanho;
        
        if (dados.celulasVazias <= 0){
            System.out.println("Jogador venceu, encerrando jogo.");
            
            System.exit(0);
        }
        
        int numeroAleatorio = random.nextInt(posicoesVazias.tamanho);
        No<Vector2> noSorteado = posicoesVazias.obterPorIndice(numeroAleatorio);
        
        dados.celulas[noSorteado.valor.x][noSorteado.valor.y].estado = EstadoCelula.COMIDA;
    }
}