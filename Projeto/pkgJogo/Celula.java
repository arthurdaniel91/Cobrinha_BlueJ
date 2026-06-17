package pkgJogo;
import java.awt.Color;
import javax.swing.JLabel;
import pkgVector.*;

/**
 * Classe contendo os atributos e métodos que representam e controlam as celulas na matriz.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/03 (YYYY/MM/DD)
 */

public class Celula{
    //Atributos principais.
    public EstadoCelula estado;
    public Vector3 cor;
    public JLabel label;
    
    /**
     * Construtor de classe
     * 
     * @param estado (EstadoCelula) Enum representando o estado atual da célula na matriz.
     */
    
    public Celula(EstadoCelula estado){
        this.estado = estado;
    }
    
    /**
     * Define a cor do label da celula a partir da cor armazenada.
     */
    
    public void aplicarCor(){
        label.setBackground(new Color(this.cor.x, this.cor.y, this.cor.z));
    }
}