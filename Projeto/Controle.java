import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import pkgListaDuplamenteLigada.*;
import pkgExceptions.*;
import pkgVector.*;
import pkgJogo.*;

/**
 * Classe que controla o comportamento do jogo e da interface do programa.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo 
 * @version 2026/06/17 (YYYY/MM/DD)
 */

public class Controle implements KeyListener{
    //Declaração de classes.
    private ListaDuplamenteLigada lista;
    private InformacoesJogo dados;
    private Logica logica;
    private Display display;
    
    //Atributos principais.
    private JFrame janelaPrincipal;
    private JFrame janelaConfigurar;
    
    private JButton botaoConfigurar;
    private JButton botaoConfirmar;
    
    private boolean menuConfigurarAberto = false;
    
    /**
     * Construtor de classe.
     * 
     * @param lista (ListaDuplamenteLigada) A lista a ser utilizada como referência para a cobra.
     * @param dados (InformacoesJogo) Dados sobre o jogo atual.
     */
    
    public Controle(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    /**
     * Inicializa o jogo com as condições iniciais declaradas.
     */
    
    public void setup(){
        //Define as informações padrões do jogo.
        dados.jogadorVivo = true;
        dados.jogadorVenceu = false;
        dados.pontuacao = 0;
        dados.movimentos = 0;
        dados.celulas = new Celula[dados.tamanhoMatriz][dados.tamanhoMatriz];
        
        //Esvazia a lista se já não estiver vazia.
        if (lista.checarListaVazia() == false){
            lista.esvaziarLista();
        }
        
        //Define as posições do corpo da cobra.
        for (int i = 0; i < 5; i++){
            Vector2 posicao = new Vector2((int)dados.tamanhoMatriz / 2, i + 1);
            
            lista.adicionarFim(posicao);
        }
        
        //Inicializa a lógica do programa.
        logica = new Logica(lista, dados);
        logica.setup();
        
        //Inicializa a interface do programa.
        display = new Display(lista, dados);
        display.setup();
        display.desenharMatriz();
        
        //Define o comportamento do botão configurar.
        botaoConfigurar = display.botaoConfigurar;
        botaoConfigurar.addActionListener(e -> abrirMenuConfiguracoes());
        
        //Define o comportamento da janela principal.
        janelaPrincipal = display.janelaPrincipal;
        janelaPrincipal.addKeyListener(this);
    }
    
    /**
     * Abre o menu configurações ao apertar no botão configurar.
     */
    
    public void abrirMenuConfiguracoes(){
        //Forma a janela configurar e declara as variáveis respectivas.
        display.montarJanelaConfigurar();
        janelaConfigurar = display.janelaConfigurar;
        menuConfigurarAberto = true;
        
        //Define o comportamento do botão confirmar.
        botaoConfirmar = display.botaoConfirmar;
        botaoConfirmar.addActionListener(e -> validarConfiguracoes());
    }
    
    /**
     * Processo de validação para as configurações inseridas pelo usuário.
     */
    
    public void validarConfiguracoes(){
        //Variável auxiliar para validar configurações.
        boolean configuracoesValidas = true;
        
        //Processo de validação das configurações.
        try {
            //Variáveis auxiliares em texto.
            String tamanhoMatrizConfigurar = display.tamanhoMatrizConfigurar.getText();
            String corVazioConfigurar = display.corVazioConfigurar.getText();
            String corParedeConfigurar = display.corParedeConfigurar.getText();
            String corCaudaConfigurar = display.corCaudaConfigurar.getText();
            String corCabecaConfigurar = display.corCabecaConfigurar.getText();
            String corComidaConfigurar = display.corComidaConfigurar.getText();
            
            //Se houver campos vazios, declarar exceção de campo vazio.
            if (tamanhoMatrizConfigurar.isBlank()) throw new CampoVazioException("Falha: um ou mais campos não preenchidos, tente novamente.");
            if (corVazioConfigurar.isBlank()) throw new CampoVazioException("Falha: um ou mais campos não preenchidos, tente novamente.");
            if (corParedeConfigurar.isBlank()) throw new CampoVazioException("Falha: um ou mais campos não preenchidos, tente novamente.");
            if (corCaudaConfigurar.isBlank()) throw new CampoVazioException("Falha: um ou mais campos não preenchidos, tente novamente.");
            if (corCabecaConfigurar.isBlank()) throw new CampoVazioException("Falha: um ou mais campos não preenchidos, tente novamente.");
            if (corComidaConfigurar.isBlank()) throw new CampoVazioException("Falha: um ou mais campos não preenchidos, tente novamente.");
            
            //Variáveis auxiliares em número e arrays de string.
            int novoTamanhoMatriz = Integer.parseInt(tamanhoMatrizConfigurar);
            String[] valoresCorVazio = corVazioConfigurar.split(",");
            String[] valoresCorParede = corParedeConfigurar.split(",");
            String[] valoresCorCauda = corCaudaConfigurar.split(",");
            String[] valoresCorCabeca = corCabecaConfigurar.split(",");
            String[] valoresCorComida = corComidaConfigurar.split(",");
            
            //Se tamanho matriz ou valor de cor forem invalidos, declarar a respectiva exceção.
            if (novoTamanhoMatriz < 8 || novoTamanhoMatriz > 50) throw new TamanhoMatrizInvalidoException("Falha: tamanho de matriz invalido, tente novamente.");
            if (valoresCorVazio.length != 3) throw new CorInvalidaException("Falha: um ou mais códigos de cor RGB invalidos, tente novamente.");
            if (valoresCorParede.length != 3) throw new CorInvalidaException("Falha: um ou mais códigos de cor RGB invalidos, tente novamente.");
            if (valoresCorCauda.length != 3) throw new CorInvalidaException("Falha: um ou mais códigos de cor RGB invalidos, tente novamente.");
            if (valoresCorCabeca.length != 3) throw new CorInvalidaException("Falha: um ou mais códigos de cor RGB invalidos, tente novamente.");
            if (valoresCorComida.length != 3) throw new CorInvalidaException("Falha: um ou mais códigos de cor RGB invalidos, tente novamente.");
            
            //Variáveis auxiliares para os valores individuais de cor RGB.
            int novaCorVazioR = Integer.parseInt(valoresCorVazio[0].trim());
            int novaCorVazioG = Integer.parseInt(valoresCorVazio[1].trim());
            int novaCorVazioB = Integer.parseInt(valoresCorVazio[2].trim());
            
            int novaCorParedeR = Integer.parseInt(valoresCorParede[0].trim());
            int novaCorParedeG = Integer.parseInt(valoresCorParede[1].trim());
            int novaCorParedeB = Integer.parseInt(valoresCorParede[2].trim());
            
            int novaCorCaudaR = Integer.parseInt(valoresCorCauda[0].trim());
            int novaCorCaudaG = Integer.parseInt(valoresCorCauda[1].trim());
            int novaCorCaudaB = Integer.parseInt(valoresCorCauda[2].trim());
            
            int novaCorCabecaR = Integer.parseInt(valoresCorCabeca[0].trim());
            int novaCorCabecaG = Integer.parseInt(valoresCorCabeca[1].trim());
            int novaCorCabecaB = Integer.parseInt(valoresCorCabeca[2].trim());
            
            int novaCorComidaR = Integer.parseInt(valoresCorComida[0].trim());
            int novaCorComidaG = Integer.parseInt(valoresCorComida[1].trim());
            int novaCorComidaB = Integer.parseInt(valoresCorComida[2].trim());
            
            //Se houver valores de cor RGB invalidos, declarar exceção valor RGB invalido.
            if (novaCorVazioR < 0 || novaCorVazioR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorVazioG < 0 || novaCorVazioG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorVazioB < 0 || novaCorVazioB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            
            if (novaCorParedeR < 0 || novaCorParedeR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorParedeG < 0 || novaCorParedeG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorParedeB < 0 || novaCorParedeB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            
            if (novaCorCaudaR < 0 || novaCorCaudaR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorCaudaG < 0 || novaCorCaudaG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorCaudaB < 0 || novaCorCaudaB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            
            if (novaCorCabecaR < 0 || novaCorCabecaR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorCabecaG < 0 || novaCorCabecaG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorCabecaB < 0 || novaCorCabecaB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            
            if (novaCorComidaR < 0 || novaCorComidaR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorComidaG < 0 || novaCorComidaG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            if (novaCorComidaB < 0 || novaCorComidaB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB invalidos.");
            
            //Se todas as configurações forem validas, aplicar ao programa.
            if (configuracoesValidas == true){
                dados.tamanhoMatriz = novoTamanhoMatriz;
                dados.corVazio = new Vector3(novaCorVazioR, novaCorVazioG, novaCorVazioB);
                dados.corParede = new Vector3(novaCorParedeR, novaCorParedeG, novaCorParedeB);
                dados.corCauda = new Vector3(novaCorCaudaR, novaCorCaudaG, novaCorCaudaB);
                dados.corCabeca = new Vector3(novaCorCabecaR, novaCorCabecaG, novaCorCabecaB);
                dados.corComida = new Vector3(novaCorComidaR, novaCorComidaG, novaCorComidaB);
                
                //Fecha as janelas e reinicia o programa.
                janelaPrincipal.dispose();
                janelaConfigurar.dispose();
                this.setup();
            }
            
        } catch (CampoVazioException exc){
            //Se houver exceção de campo vazio, declarar configurações invalidas.
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (TamanhoMatrizInvalidoException exc){
            //Se houver exceção de tamanho matriz invalida, declarar configurações invalidas.
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (CorInvalidaException exc){
            //Se houver exceção de cor invalida, declarar configurações invalidas.
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (ValorRGBInvalidoException exc){
            //Se houver exceção de valor RGB invalido, declarar configurações invalidas.
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (NumberFormatException exc){
            //Se houver exceção de formatação de número invalida, declarar configurações invalidas.
            display.mostrarMensagem("Falha: um ou mais valores não são válidos, por favor utilize apenas números, tente novamente.");
            configuracoesValidas = false;
            
        } catch (Exception exc){
            //Se houver outra exceção não declarada acima, declarar configurações invalidas.
            display.mostrarMensagem("Falha: algo de errado ocorreu, tente novamente.");
            System.out.println(exc.getMessage());
            configuracoesValidas = false;
        }
    }
    
    /**
     * Evento de entrada por teclado.
     */
    
    @Override
    public void keyPressed(KeyEvent tecla){
        //Se a tecla apertada for R e o jogador tiver morrido/vencido, reiniciar jogo.
        if (tecla.getKeyCode() == KeyEvent.VK_R){
            if (dados.jogadorVivo == true && dados.jogadorVenceu == false) return;
            
            janelaPrincipal.dispose();
            this.setup();
        }
        
        //Se o jogador não estiver vivo ou tiver vencido, parar aqui.
        if (dados.jogadorVivo == false) return;
        if (dados.jogadorVenceu == true) return;
        
        //Variável auxiliar para direção de movimento.
        Vector2 direcao = new Vector2(0, 0);
        
        //Se a tecla apertada for W ou a setinha para cima, declarar direção para cima.
        if (tecla.getKeyCode() == KeyEvent.VK_UP || tecla.getKeyCode() == KeyEvent.VK_W){
            direcao.x = 0;
            direcao.y = 1;
        }
        
        //Se a tecla apertada for S ou a setinha para baixo, declarar direção para baixo.
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN || tecla.getKeyCode() == KeyEvent.VK_S){
            direcao.x = 0;
            direcao.y = -1;
        }
        
        //Se a tecla apertada for D ou a setinha para direita, declarar direção para direita.
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT || tecla.getKeyCode() == KeyEvent.VK_D){
            direcao.x = 1;
            direcao.y = 0;
        }
        
        //Se a tecla apertada for A ou a setinha para esquerda, declarar direção para esquerda.
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT || tecla.getKeyCode() == KeyEvent.VK_A){
            direcao.x = -1;
            direcao.y = 0;
        }
        
        //Se a direção for nula, parar aqui.
        if (direcao.x == 0 && direcao.y == 0) return;
        
        //Movimenta a cobrinha de acordo com a direção declarada e atualiza a matriz.
        logica.movimentarCobra(direcao);
        display.desenharMatriz();
        display.atualizarInformacoes();
        
        //Se o jogador venceu, encerrar jogo.
        if (dados.jogadorVenceu == true){
            display.mostrarMensagem("Jogador obteve todas as comidas e venceu, encerrando jogo.");
            
            return;
        }
        
        //Se o jogador morreu, encerrar jogo.
        if (dados.jogadorVivo == false){
            display.mostrarMensagem("Jogador colidiu com a parede ou com o próprio corpo, encerrando jogo.");
            
            return;
        }
    }
    
    /**
     * Evento de saida por teclado.
     */
    
    @Override
    public void keyReleased(KeyEvent tecla){
        //Função vazia.
    }
    
    /**
     * Evento de entrada por teclado em campo de digitação.
     */
    
    @Override
    public void keyTyped(KeyEvent tecla){
        //Função vazia.
    }
}