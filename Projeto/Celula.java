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
    public Vector3 cor;
    public JLabel label;
    
    public Celula(int x, int y, EstadoCelula estado){
        this.x = x;
        this.y = y;
        this.estado = estado;
    }
    
    public void aplicarCor(){
        label.setBackground(new Color(this.cor.x, this.cor.y, this.cor.z));
    }
}