import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgMatrizInterface.*;
import java.util.Scanner;

/**
 * Escreva uma descrição da classe Aplicativo aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Aplicativo{
    private static ListaDuplamenteLigada lista = new ListaDuplamenteLigada<>();
    private static MatrizSwing matriz;
    
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String args[]){
        // System.out.println("\nTamanho da matriz (Min 25):\n");
        int tamanho;
        
        // do {
            // tamanho = scanner.nextInt();
            
            // if (tamanho < 25){
                // System.out.println("Tamanho invalido, tente novamente.");
            // }
        // } while (tamanho < 25);
        
        // matriz = new MatrizSwing(lista, tamanho);
        
        tamanho = 10;
        matriz = new MatrizSwing(lista, tamanho);
        
        for (int i = 0; i < 5; i++){
            Vector2 posicao = new Vector2((int)tamanho / 2, i + 3);
            
            lista.adicionarFim(posicao);
        }
        
        matriz.setup();
        // lista.imprimirLista();
    }
}