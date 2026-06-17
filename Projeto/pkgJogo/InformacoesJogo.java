package pkgJogo;
import pkgVector.Vector3;

/**
 * Classe contendo os atributos que representam o estado atual do jogo.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/16 (YYYY/MM/DD)
 */

public class InformacoesJogo{
    //Atributos principais.
    public boolean jogadorVivo;
    public boolean jogadorVenceu;
    public int pontuacaoTotal;
    public int movimentosTotais;
    
    public int tamanhoMatriz;
    public int pontuacao;
    public int movimentos;
    
    public Celula[][] celulas;
    public int celulasVazias;
    
    public Vector3 corVazio;
    public Vector3 corParede;
    public Vector3 corCauda;
    public Vector3 corCabeca;
    public Vector3 corComida;
}