package pkgExceptions;

/**
 * Exceção customizada para campos vazios.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/16 (YYYY/MM/DD)
 */

public class CampoVazioException extends Exception{
    /**
     * Construtor de classe.
     * 
     * @param mensagem (String) Mensagem a ser compartilhada através da exceção.
     */
    
    public CampoVazioException(String mensagem){
        super(mensagem);
    }
}