package pkgExceptions;

/**
 * Exceção customizada para valores de cor RGB invalidos.
 * 
 * @author Arthur Daniel, Arthur Oliveira, João Paulo
 * @version 2026/06/16 (YYYY/MM/DD)
 */

public class ValorRGBInvalidoException extends Exception{
    /**
     * Construtor de classe.
     * 
     * @param mensagem (String) Mensagem a ser compartilhada através da exceção.
     */
    
    public ValorRGBInvalidoException(String mensagem){
        super(mensagem);
    }
}