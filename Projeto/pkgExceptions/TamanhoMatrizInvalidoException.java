package pkgExceptions;

/**
 * Exceção customizada para tamanho de matriz invalido.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/16 (YYYY/MM/DD)
 */

public class TamanhoMatrizInvalidoException extends Exception{
    /**
     * Construtor de classe.
     * 
     * @param mensagem (String) Mensagem a ser compartilhada através da exceção.
     */
    
    public TamanhoMatrizInvalidoException(String mensagem){
        super(mensagem);
    }
}