package pkgMatrizInterface;

/**
 * Escreva uma descrição da classe Espaco aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Celula{
    public final int x;
    public final int y;
    public EstadoCelula estado;
    
    public Celula(int x, int y){
        this.x = x;
        this.y = y;
    }
}