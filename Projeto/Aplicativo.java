import pkgListaDuplamenteLigada.*;
import pkgVector.Vector2;

/**
 * Escreva uma descrição da classe Aplicativo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Aplicativo{
    private static ListaDuplamenteLigada lista = new ListaDuplamenteLigada<>();
    
    public static void main(String args[]){
        for (int i = 1; i <= 10; i++){
            for (int j = 1; j <= 10; j++){
                lista.adicionarFim(new Vector2(i, j));
                
                Vector2 valor = (Vector2)lista.fim.valor;
                
                System.out.print("(" + valor.x + ", " + valor.y + ")");
            }
            
            System.out.println("\n");
        }
    }
}