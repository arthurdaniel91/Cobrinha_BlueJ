import pkgListaDuplamenteLigada.*;
import pkgVector.*;
import pkgJogo.*;

/**
 * Classe principal do programa.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/17 (YYYY/MM/DD)
 */

public class Aplicativo{
    //Declarações de classes.
    private static ListaDuplamenteLigada lista = new ListaDuplamenteLigada<>();
    private static InformacoesJogo dados = new InformacoesJogo();
    private static Controle controle;
    
    /**
     * Função principal do programa.
     */
    
    public static void main(String args[]){
        //Define tamanho de matriz padrão e variáveis iniciais.
        dados.tamanhoMatriz = 20; //Padrão: 20
        dados.pontuacaoTotal = 0; //Padrão: 0
        dados.movimentosTotais = 0; //Padrão: 0
        
        //Define as cores padrões do jogo.
        dados.corVazio = new Vector3(150, 150, 150); //Padrão: 150, 150, 150
        dados.corParede = new Vector3(0, 0, 0); //Padrão: 0, 0, 0
        dados.corCauda = new Vector3(0, 100, 255); //Padrão: 0, 100, 255
        dados.corCabeca = new Vector3(100, 255, 0); //Padrão: 100, 255, 0
        dados.corComida = new Vector3(255, 0, 0); //Padrão: 255, 0, 0
        
        //Inicializa o jogo com os dados definidos.
        controle = new Controle(lista, dados);
        controle.setup();
    }
}