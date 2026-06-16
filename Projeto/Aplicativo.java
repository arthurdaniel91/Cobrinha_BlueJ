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
    private static InformacoesJogo dados = new InformacoesJogo();
    private static Controle controle;
    
    // private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String args[]){
        int tamanhoMatrizInicial = 10;
        int tamanhoCobraInicial = 5;
        
        controle = new Controle(lista, dados);
        controle.setup(tamanhoMatrizInicial, tamanhoCobraInicial);
    }
}