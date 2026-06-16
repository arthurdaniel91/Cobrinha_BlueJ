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
 * Escreva uma descrição da classe Controle aqui.
 * 
 * @author (seu nome) 
 * @version (um número da versão ou uma data)
 */

public class Controle implements KeyListener{
    private ListaDuplamenteLigada lista;
    private InformacoesJogo dados;
    private Logica logica;
    private Display display;
    
    private JFrame janelaPrincipal;
    private JFrame janelaConfigurar;
    
    private JButton botaoConfigurar;
    private JButton botaoConfirmar;
    
    private boolean menuConfigurarAberto = false;
    
    public Controle(ListaDuplamenteLigada lista, InformacoesJogo dados){
        this.lista = lista;
        this.dados = dados;
    }
    
    public void setup(){
        dados.jogoRodando = false;
        dados.celulas = new Celula[dados.tamanhoMatriz][dados.tamanhoMatriz];
        
        if (!lista.checarListaVazia()){
            lista.esvaziarLista();
        }
        
        for (int i = 0; i < dados.tamanhoCobra; i++){
            Vector2 posicao = new Vector2((int)dados.tamanhoMatriz / 2, i + 3);
            
            lista.adicionarFim(posicao);
        }
        
        
        logica = new Logica(lista, dados);
        logica.setup();
        
        display = new Display(lista, dados);
        display.setup();
        display.desenharMatriz();
        
        botaoConfigurar = display.botaoConfigurar;
        botaoConfigurar.addActionListener(e -> abrirMenuConfiguracoes());
        
        janelaPrincipal = display.janelaPrincipal;
        janelaPrincipal.addKeyListener(this);
    }
    
    public void abrirMenuConfiguracoes(){
        display.montarJanelaConfigurar();
        janelaConfigurar = display.janelaConfigurar;
        menuConfigurarAberto = true;
        
        botaoConfirmar = display.botaoConfirmar;
        botaoConfirmar.addActionListener(e -> validarConfiguracoes());
    }
    
    public void validarConfiguracoes(){
        boolean configuracoesValidas = true;
        
        String tamanhoMatrizConfigurar = display.tamanhoMatrizConfigurar.getText();
        String corVazioConfigurar = display.corVazioConfigurar.getText();
        String corParedeConfigurar = display.corParedeConfigurar.getText();
        String corCaudaConfigurar = display.corCaudaConfigurar.getText();
        String corCabecaConfigurar = display.corCabecaConfigurar.getText();
        String corComidaConfigurar = display.corComidaConfigurar.getText();
        
        try {
            if (tamanhoMatrizConfigurar.isBlank()) throw new CampoVazioException("Falha: houve um ou mais campos não preenchidos, tente novamente.");
            if (corVazioConfigurar.isBlank()) throw new CampoVazioException("Falha: houve um ou mais campos não preenchidos, tente novamente.");
            if (corParedeConfigurar.isBlank()) throw new CampoVazioException("Falha: houve um ou mais campos não preenchidos, tente novamente.");
            if (corCaudaConfigurar.isBlank()) throw new CampoVazioException("Falha: houve um ou mais campos não preenchidos, tente novamente.");
            if (corCabecaConfigurar.isBlank()) throw new CampoVazioException("Falha: houve um ou mais campos não preenchidos, tente novamente.");
            if (corComidaConfigurar.isBlank()) throw new CampoVazioException("Falha: houve um ou mais campos não preenchidos, tente novamente.");
            
            int novoTamanhoMatriz = Integer.parseInt(tamanhoMatrizConfigurar);
            String[] valoresCorVazio = corVazioConfigurar.split(",");
            String[] valoresCorParede = corParedeConfigurar.split(",");
            String[] valoresCorCauda = corCaudaConfigurar.split(",");
            String[] valoresCorCabeca = corCabecaConfigurar.split(",");
            String[] valoresCorComida = corComidaConfigurar.split(",");
            
            if (valoresCorVazio.length != 3) throw new CorInvalidaException("Falha: valor de cor RGB invalido(s), tente novamente.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (valoresCorParede.length != 3) throw new CorInvalidaException("Falha: valor de cor RGB invalido(s), tente novamente.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (valoresCorCauda.length != 3) throw new CorInvalidaException("Falha: valor de cor RGB invalido(s), tente novamente.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (valoresCorCabeca.length != 3) throw new CorInvalidaException("Falha: valor de cor RGB invalido(s), tente novamente.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (valoresCorComida.length != 3) throw new CorInvalidaException("Falha: valor de cor RGB invalido(s), tente novamente.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            
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
            
            if (novaCorVazioR < 0 || novaCorVazioR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorVazioG < 0 || novaCorVazioG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorVazioB < 0 || novaCorVazioB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            
            if (novaCorParedeR < 0 || novaCorParedeR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorParedeG < 0 || novaCorParedeG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorParedeB < 0 || novaCorParedeB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            
            if (novaCorCaudaR < 0 || novaCorCaudaR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorCaudaG < 0 || novaCorCaudaG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorCaudaB < 0 || novaCorCaudaB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            
            if (novaCorCabecaR < 0 || novaCorCabecaR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorCabecaG < 0 || novaCorCabecaG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorCabecaB < 0 || novaCorCabecaB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            
            if (novaCorComidaR < 0 || novaCorComidaR > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorComidaG < 0 || novaCorComidaG > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            if (novaCorComidaB < 0 || novaCorComidaB > 255) throw new ValorRGBInvalidoException("Falha: um ou mais valores RGB incorretos.\nNota: Um valor RGB segue o seguinte formato: (0-255, 0-255, 0-255)");
            
            dados.tamanhoMatriz = novoTamanhoMatriz;
            dados.corVazio = new Vector3(novaCorVazioR, novaCorVazioG, novaCorVazioB);
            dados.corParede = new Vector3(novaCorParedeR, novaCorParedeG, novaCorParedeB);
            dados.corCauda = new Vector3(novaCorCaudaR, novaCorCaudaG, novaCorCaudaB);
            dados.corCabeca = new Vector3(novaCorCabecaR, novaCorCabecaG, novaCorCabecaB);
            dados.corComida = new Vector3(novaCorComidaR, novaCorComidaG, novaCorComidaB);
            
            if (configuracoesValidas){
                janelaPrincipal.dispose();
                janelaConfigurar.dispose();
                this.setup();
            }
            
        } catch (CampoVazioException exc){
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (CorInvalidaException exc){
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (ValorRGBInvalidoException exc){
            display.mostrarMensagem(exc.getMessage());
            configuracoesValidas = false;
            
        } catch (NumberFormatException exc){
            display.mostrarMensagem("Falha: um ou mais valores não são válidos, por favor utilize apenas números, tente novamente.");
            configuracoesValidas = false;
            
        } catch (Exception exc){
            display.mostrarMensagem("Falha: algo de errado ocorreu, tente novamente.");
            System.out.println(exc.getMessage());
            configuracoesValidas = false;
        }
    }
    
    @Override
    public void keyPressed(KeyEvent tecla){
        if (tecla.getKeyCode() == KeyEvent.VK_ENTER && !dados.jogoRodando){
            if (menuConfigurarAberto == true) return;
            dados.jogoRodando = true;
            
            display.atualizarInformacoes();
        }
        
        // if (!dados.jogoRodando) return;
        
        if (tecla.getKeyCode() == KeyEvent.VK_UP){
            logica.movimentarCobra(new Vector2(0, 1));
            display.desenharMatriz();
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_DOWN){
            logica.movimentarCobra(new Vector2(0, -1));
            display.desenharMatriz();
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_RIGHT){
            logica.movimentarCobra(new Vector2(1, 0));
            display.desenharMatriz();
        }
        
        if (tecla.getKeyCode() == KeyEvent.VK_LEFT){
            logica.movimentarCobra(new Vector2(-1, 0));
            display.desenharMatriz();
        }
    }
    
    @Override
    public void keyReleased(KeyEvent tecla){
        
    }
    
    @Override
    public void keyTyped(KeyEvent tecla){
        
    }
}