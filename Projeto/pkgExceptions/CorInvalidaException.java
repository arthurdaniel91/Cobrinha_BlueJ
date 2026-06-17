package pkgExceptions;

/**
 * Exceção customizada para cores invalidas.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/16 (YYYY/MM/DD)
 */

public class CorInvalidaException extends Exception{
    /**
     * Construtor de classe.
     * 
     * @param mensagem (String) Mensagem a ser compartilhada através da exceção.
     */
    
    public CorInvalidaException(String mensagem){
        super(mensagem);
    }
}