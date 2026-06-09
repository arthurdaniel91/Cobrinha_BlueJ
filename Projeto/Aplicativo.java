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
    private static MatrizSwing matriz = new MatrizSwing(50);
    
    public static void main(String args[]){
        matriz.setup();
    }
}