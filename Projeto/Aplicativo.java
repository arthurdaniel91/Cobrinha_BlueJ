import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgJogo.*;

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
    
    public static void main(String args[]){
        dados.tamanhoMatriz = 20; //Padrão: 20
        dados.pontuacaoTotal = 0; //Padrão: 0
        dados.movimentosTotais = 0; //Padrão: 0
        
        dados.corVazio = new Vector3(150, 150, 150); //Padrão: 150, 150, 150
        dados.corParede = new Vector3(0, 0, 0); //Padrão: 0, 0, 0
        dados.corCauda = new Vector3(0, 100, 255); //Padrão: 0, 100, 255
        dados.corCabeca = new Vector3(100, 255, 0); //Padrão: 100, 255, 0
        dados.corComida = new Vector3(255, 0, 0); //Padrão: 255, 0, 0
        
        controle = new Controle(lista, dados);
        controle.setup();
    }
}