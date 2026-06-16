package pkgJogo;
import pkgVector.Vector3;

/**
 * Escreva uma descrição da classe InformacoesJogo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class InformacoesJogo{
    public boolean jogoRodando;
    public boolean jogadorVivo;
    
    public int tamanhoMatriz;
    public int tamanhoCobra;
    public int pontuacao;
    
    public Celula[][] celulas;
    public int celulasVazias;
    
    public Vector3 corVazio;
    public Vector3 corParede;
    public Vector3 corCauda;
    public Vector3 corCabeca;
    public Vector3 corComida;
}