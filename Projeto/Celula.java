import java.awt.Color;
import javax.swing.JLabel;
import pkgVector.*;

/**
 * Escreva uma descrição da classe Espaco aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Celula{
    public final int x, y;
    public EstadoCelula estado;
    public JLabel labelCelula;
    
    public Celula(int x, int y, JLabel labelCelula){
        this.x = x;
        this.y = y;
        this.labelCelula = labelCelula;
    }
    
    public void definirCor(Vector3 cor){
        labelCelula.setBackground(new Color(cor.x, cor.y, cor.z));
    }
}