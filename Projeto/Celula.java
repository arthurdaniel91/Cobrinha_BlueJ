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
    public JLabel label;
    
    public Celula(int x, int y, EstadoCelula estado, JLabel label){
        this.x = x;
        this.y = y;
        this.estado = estado;
        this.label = label;
    }
    
    public void definirEstado(EstadoCelula estado){
        this.estado = estado;
    }
    
    public void definirCor(Vector3 cor){
        label.setBackground(new Color(cor.x, cor.y, cor.z));
    }
}