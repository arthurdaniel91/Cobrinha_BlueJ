import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgMatrizInterface.*;

/**
 * Escreva uma descrição da classe Aplicativo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Aplicativo{
    private static ListaDuplamenteLigada lista = new ListaDuplamenteLigada<>();
    private static MatrizSwing matriz = new MatrizSwing(lista, 50);
    
    public static void main(String args[]){
        matriz.setup();
        
        for (int i = 0; i < 25; i++){
            Vector2 posicao = new Vector2(10, i + 10);
            
            lista.adicionarFim(posicao);
        }
        
        matriz.atualizarMatriz();
        lista.imprimirLista();
    }
}