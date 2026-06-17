package pkgJogo;
import pkgVector.Vector3;

/**
 * Escreva uma descrição da classe InformacoesJogo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class InformacoesJogo{
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